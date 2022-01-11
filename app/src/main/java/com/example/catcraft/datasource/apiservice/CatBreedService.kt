package com.example.catcraft.datasource.apiservice

import com.example.catcraft.datasource.model.CatBreedData
import com.example.catcraft.network.utils.Cacheable
import retrofit2.Response
import retrofit2.http.GET

interface CatBreedService {

    @GET("v1/breeds")
    suspend fun getCatBreedList(): Response<List<CatBreedData>>
}