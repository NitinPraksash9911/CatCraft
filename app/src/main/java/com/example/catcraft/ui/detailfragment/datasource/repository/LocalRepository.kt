package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.ui.detailfragment.datasource.cacheservice.BreadDao

class LocalRepository(private val dao: BreadDao) {

    suspend fun getLocalData() = dao.getBreedData()
}