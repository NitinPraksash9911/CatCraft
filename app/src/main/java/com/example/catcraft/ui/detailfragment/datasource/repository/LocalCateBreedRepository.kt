package com.example.catcraft.ui.detailfragment.datasource.repository

import com.example.catcraft.network.utils.getResult
import com.example.catcraft.ui.detailfragment.datasource.model.CatBreedData
import retrofit2.Response
import javax.inject.Inject

class LocalCateBreedRepository @Inject constructor(
//    private val dao: BreadDao
) {

    suspend fun getLocalData() {
        getResult {
            getDataLocal()
        }
    }


    suspend fun getDataLocal(): Response<List<CatBreedData>> {
        return Response.success(listOf(CatBreedData("1", null, "name", "asda", "asdas")))
    }

}