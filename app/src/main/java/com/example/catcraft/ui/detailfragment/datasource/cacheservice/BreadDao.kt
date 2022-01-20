package com.example.catcraft.ui.detailfragment.datasource.cacheservice

import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData

interface BreadDao {

    suspend fun getBreedData(): List<CatBreedData>
}