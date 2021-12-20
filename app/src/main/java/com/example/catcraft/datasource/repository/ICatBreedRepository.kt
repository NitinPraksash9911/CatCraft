package com.example.catcraft.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.datasource.model.CatBreedData

interface ICatBreedRepository {
    suspend fun getCatBreedList(): Resource<List<CatBreedData>>
}