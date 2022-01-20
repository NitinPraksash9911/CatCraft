package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData

interface ICatBreedRemoteRepository {
    suspend fun getCatBreedList(): Resource<List<CatBreedData>>
}