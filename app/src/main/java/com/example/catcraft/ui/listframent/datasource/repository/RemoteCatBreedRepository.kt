package com.example.catcraft.ui.listframent.datasource.repository

import com.example.catcraft.ui.listframent.datasource.apis.CatBreedService
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.nitin.networkerrorhandler.datasource.model.Resource
import com.nitin.networkerrorhandler.utils.getResult
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