package com.example.catcraft.ui.listframent.datasource.repository

import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.nitin.networkerrorhandler.datasource.model.Resource

interface IDataCatBreedRepository {
    suspend fun getCatBreedList(): Resource<List<CatBreedData>>
    suspend fun getDataFromLocal(): Resource<List<CatBreedData>>

}