package com.example.catcraft.arch

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Error(val errorMsg: String) : ViewState<Nothing>()
    data class Success<T>(val item: T) : ViewState<T>()
}

