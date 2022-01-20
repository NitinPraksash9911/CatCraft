package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData

interface IDataCatBreedRepository {
    suspend fun getCatBreedList(): Resource<List<CatBreedData>>
    suspend fun getDataFromLocal():Resource<List<CatBreedData>>
}