package com.example.catcraft.di

import com.example.catcraft.BuildConfig
import com.example.catcraft.ui.detailfragment.datasource.apiservice.CatBreedService
import com.example.catcraft.ui.detailfragment.datasource.repository.CatBreedRemoteRemoteRepository
import com.example.catcraft.ui.detailfragment.datasource.repository.ICatBreedRemoteRepository
import com.example.catcraft.network.interceptor.LoggingInterceptor
import com.example.catcraft.network.interceptor.NetworkCacheInterceptor
import com.example.catcraft.network.interceptor.NetworkStatusInterceptor
import com.example.catcraft.network.interceptor.OfflineCacheInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun getGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    /**

    There’s an important detail you must consider before adding the other interceptors. Like Retrofit’s type converters,
    interceptors are called in order. So, if you do something like:

    OkHttpClient.Builder()
    .addInterceptor(A)
    .addInterceptor(C)
    .addInterceptor(B)
    The interceptors will run in that order: A → C → B.


    networkStatusInterceptor first. If the device doesn’t have an internet connection,
    there’s no need to allow the request to go further.
    then httpLoggingInterceptor, which wraps LoggingInterceptor.

    Is it weird to put httpLoggingInterceptor last? Should it be the first one to run,
    so it can log even authenticationInterceptor’s requests?

    Nope! If you add it first, it’ll run while there’s still nothing to log.
    Interceptors work on the chain they receive, so you want the logging interceptor to get the final chain.


     */

    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        networkStatusInterceptor: NetworkStatusInterceptor,
        networkCacheInterceptor: NetworkCacheInterceptor,
        offlineCacheInterceptor: OfflineCacheInterceptor
    ): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(networkStatusInterceptor)//1
            .apply {
                if (BuildConfig.DEBUG) addInterceptor(loggingInterceptor)//2
            }
            .addNetworkInterceptor(networkCacheInterceptor)//3
            .addInterceptor(offlineCacheInterceptor)//4
            .readTimeout(15, SECONDS)
            .writeTimeout(15, SECONDS)
            .connectTimeout(15, SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        factory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(factory)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(loggingInterceptor: LoggingInterceptor): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(loggingInterceptor)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideCatBreedService(retrofit: Retrofit): CatBreedService = retrofit.create(CatBreedService::class.java)

    @Singleton
    @Provides
    fun providesCatBreedRepository(breedService: CatBreedService): ICatBreedRemoteRepository = CatBreedRemoteRemoteRepository(breedService)

}