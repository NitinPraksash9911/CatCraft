package com.example.catcraft.ui.listframent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.example.catcraft.ui.listframent.datasource.repository.IDataCatBreedRepository
import com.example.catcraft.utils.DispatcherProvider
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse
import com.jumpingminds.networkrequesthandler.datasource.model.NetworkResource
import com.jumpingminds.networkrequesthandler.datasource.model.ViewStateResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedViewModel @Inject constructor(
    private val repositoryData: IDataCatBreedRepository,
    private val dispatchers: DispatcherProvider
) : ViewModel() {

    private var _breedListData = MutableStateFlow<ViewStateResource<List<CatBreedData>>>(ViewStateResource.ViewLoading)

    val breedListData: StateFlow<ViewStateResource<List<CatBreedData>>>
        get() = _breedListData

    init {
        fetchCatBreeds()
    }

    fun fetchCatBreeds() {
        viewModelScope.launch {
            val result = repositoryData.getCatBreedList()
            handlerCatBreedResult(result)
            val string = "ss"
            string[0]
        }

    }

    private fun handlerCatBreedResult(result: NetworkResource<List<CatBreedData>>) {
        when (result) {
            is NetworkResource.Success -> showCatBreedList(result.data)
            is NetworkResource.Error -> {
                showError(result.errorResponse)
            }
        }
    }

    private fun showError(errorResponse: ErrorResponse) {
        _breedListData.value = ViewStateResource.ViewError(errorResponse)
    }

    private fun showCatBreedList(value: List<CatBreedData>) {
        _breedListData.value = ViewStateResource.ViewSuccess(value)
    }

}