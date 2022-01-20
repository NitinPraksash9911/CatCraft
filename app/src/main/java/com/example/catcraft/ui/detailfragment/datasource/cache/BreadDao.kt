package com.example.catcraft.ui.detailfragment.datasource.cache

import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData

interface BreadDao {

    suspend fun getBreedData(): List<CatBreedData>
}