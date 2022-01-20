package com.example.catcraft.ui.listframent.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.ui.listframent.datasource.apis.CatBreedService
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.example.catcraft.network.utils.getResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class RemoteCatBreedRepository @Inject constructor(
    private var breedService: CatBreedService
) {

    suspend fun getCatBreedList(): Resource<List<CatBreedData>> {
        return getResult {
            breedService.getCatBreedList()
        }
    }
}