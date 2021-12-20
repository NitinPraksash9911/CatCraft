package com.example.catcraft.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catcraft.arch.Resource
import com.example.catcraft.arch.ViewState
import com.example.catcraft.datasource.model.CatBreedData
import com.example.catcraft.datasource.repository.ICatBreedRepository
import com.example.catcraft.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatBreedViewModel @Inject constructor(
    private val repository: ICatBreedRepository,
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
            when (val result = repository.getCatBreedList()) {
                is Resource.Success -> {
                    _breedListData.value = ViewState.Success(result.value)
                }
                is Resource.Error -> {
                    _breedListData.value = ViewState.Error(result.msg)
                }
            }
        }

    }

}