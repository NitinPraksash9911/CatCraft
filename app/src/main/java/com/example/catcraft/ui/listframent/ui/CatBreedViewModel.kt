package com.example.catcraft.ui.listframent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcraft.arch.Resource
import com.example.catcraft.arch.Resource.Error
import com.example.catcraft.arch.Resource.HttpErrors.BadGateWay
import com.example.catcraft.arch.Resource.HttpErrors.InternalServerError
import com.example.catcraft.arch.Resource.HttpErrors.RemovedResourceFound
import com.example.catcraft.arch.Resource.HttpErrors.ResourceForbidden
import com.example.catcraft.arch.Resource.HttpErrors.ResourceNotFound
import com.example.catcraft.arch.Resource.HttpErrors.ResourceRemoved
import com.example.catcraft.arch.Resource.InvalidData
import com.example.catcraft.arch.Resource.NetworkException
import com.example.catcraft.arch.Resource.Success
import com.example.catcraft.arch.ViewState
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.example.catcraft.ui.listframent.datasource.repository.IDataCatBreedRepository
import com.example.catcraft.utils.DispatcherProvider
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

    private var _breedListData = MutableStateFlow<ViewState<List<CatBreedData>>>(ViewState.Loading)

    val breedListData: StateFlow<ViewState<List<CatBreedData>>>
        get() = _breedListData

    init {
        fetchCatBreeds()
    }

    fun fetchCatBreeds() {
        viewModelScope.launch(dispatchers.io) {
            val result = repositoryData.getCatBreedList()
            handlerCatBreedResult(result)

        }

    }

    private fun handlerCatBreedResult(result: Resource<List<CatBreedData>>) {
        when (result) {
            is Success -> {
                showCatBreedList(result.value)
            }
            is ResourceForbidden -> {
                handleError(result.exception)
            }
            is ResourceNotFound -> {
                handleError(result.exception)
            }
            is InternalServerError -> {
                handleError(result.exception)
            }
            is BadGateWay -> {
                handleError(result.exception)
            }
            is ResourceRemoved -> {
                handleError(result.exception)
            }
            is RemovedResourceFound -> {
                handleError(result.exception)
            }
            is Error -> {
                handleError(result.error)
            }
            is NetworkException -> {
                handleError(result.error)
            }
            is InvalidData -> {
                showEmptyView()
            }
        }
    }

    private fun showEmptyView() {

    }

    private fun handleError(error: String) {
        _breedListData.value = ViewState.Error(error)
    }

    private fun showCatBreedList(value: List<CatBreedData>) {
        _breedListData.value = ViewState.Success(value)
    }

}