package com.example.catcraft.ui.listframent.di


import com.example.catcraft.ui.listframent.datasource.apis.CatBreedService
import com.example.catcraft.ui.listframent.datasource.repository.DataCatBreedRepository
import com.example.catcraft.ui.listframent.datasource.repository.RemoteCatBreedRepository
import com.example.catcraft.ui.listframent.datasource.repository.IDataCatBreedRepository
import com.example.catcraft.ui.listframent.datasource.repository.LocalCateBreedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIBreedRepository {

    @Singleton
    @Provides
    fun providesCatBreedRepository(
        localCateBreedRepository: LocalCateBreedRepository,
        remoteCatBreedRepository: RemoteCatBreedRepository
    ): IDataCatBreedRepository =
        DataCatBreedRepository(localCateBreedRepository, remoteCatBreedRepository)

    @Singleton
    @Provides
    fun provideCatBreedService(retrofit: Retrofit): CatBreedService =
        retrofit.create(CatBreedService::class.java)

}