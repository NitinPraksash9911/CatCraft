package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.ui.detailfragment.datasource.apiservice.CatBreedService
import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData
import com.example.catcraft.network.utils.getResult
import javax.inject.Inject

class CatBreedRemoteRepository @Inject constructor(
    private var breedService: CatBreedService
) : ICatBreedRemoteRepository {

    override suspend fun getCatBreedList(): Resource<List<CatBreedData>> {
        return getResult {
            breedService.getCatBreedList()
        }
    }

}