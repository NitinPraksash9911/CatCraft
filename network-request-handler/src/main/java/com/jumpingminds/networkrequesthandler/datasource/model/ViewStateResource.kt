package com.jumpingminds.networkrequesthandler.datasource.model


sealed class ViewStateResource<out T> {
    object ViewLoading : ViewStateResource<Nothing>()
    data class ViewError(val errorResponse: ErrorResponse) : ViewStateResource<Nothing>()
    data class ViewSuccess<T>(val item: T) : ViewStateResource<T>()
}

