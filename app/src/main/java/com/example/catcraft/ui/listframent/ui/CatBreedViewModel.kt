package com.example.catcraft.ui.listframent.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcraft.arch.ViewState
import com.example.catcraft.ui.listframent.datasource.model.CatBreedData
import com.example.catcraft.ui.listframent.datasource.repository.IDataCatBreedRepository
import com.example.catcraft.utils.DispatcherProvider
import com.nitin.networkerrorhandler.utils.ErrorResponse
import com.nitin.networkerrorhandler.datasource.model.Resource
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

            val string = "ss"
            string[0]
        }

    }

    private fun handlerCatBreedResult(result: Resource<List<CatBreedData>>) {
        when (result) {
            is Resource.Success -> showCatBreedList(result.value)
            is Resource.Error -> {
                showError(result.errorResponse)
            }
        }
    }

   private fun showError(errorResponse: ErrorResponse) {
        _breedListData.value = ViewState.Error(errorResponse)
    }

    private fun showCatBreedList(value: List<CatBreedData>) {
        _breedListData.value = ViewState.Success(value)
    }

}