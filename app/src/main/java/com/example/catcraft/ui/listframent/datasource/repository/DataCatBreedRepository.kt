package com.example.catcraft.ui.listframent.datasource.repository

import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.nitin.networkerrorhandler.datasource.model.Resource
import com.nitin.networkerrorhandler.utils.getResult

class DataCatBreedRepository(
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