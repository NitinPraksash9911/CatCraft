package com.example.catcraft.ui.listframent.datasource.repository

import com.example.catcraft.arch.Resource
import com.example.catcraft.network.utils.getResult
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import javax.inject.Inject

class DataCatBreedRepository @Inject constructor(
    private val localCateBreedRepository: LocalCateBreedRepository,
    private val remoteCatBreedRepository: RemoteCatBreedRepository
) : IDataCatBreedRepository {


    override suspend fun getCatBreedList(): Resource<List<CatBreedData>> {
        return remoteCatBreedRepository.getCatBreedList()
    }

    override suspend fun getDataFromLocal(): Resource<List<CatBreedData>> {
        return getResult {
            localCateBreedRepository.getDataLocal()
        }

    }
}