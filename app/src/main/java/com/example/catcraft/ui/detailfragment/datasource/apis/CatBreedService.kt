package com.example.catcraft.ui.detailfragment.datasource.apis

import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData
import retrofit2.Response
import retrofit2.http.GET

interface CatBreedService {

    @GET("v1/breeds")
    suspend fun getCatBreedList(): Response<List<CatBreedData>>
}