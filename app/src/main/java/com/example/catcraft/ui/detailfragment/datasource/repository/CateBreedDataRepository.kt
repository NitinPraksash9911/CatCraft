package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData

class CateBreedDataRepository(
    private val localRepository: LocalRepository,
    private val remoteRepository: CatBreedRemoteRepository
) {


    suspend fun getBreedData(): Resource<List<CatBreedData>> {
        var data = localRepository.getLocalData()
        return if (data == null) {
            remoteRepository.getCatBreedList()
        } else Resource.Success(data)
    }
}