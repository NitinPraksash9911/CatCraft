package com.jumpingminds.networkrequesthandler.datasource.model
/**
@author: Nitin Prakash
@date: 12-Feb-2022

This class represent the Network Resource which mean what is the status of result while executing the any network request
 */
sealed class NetworkResource<out T> {
    data class Success<out T>(val data: T) : NetworkResource<T>()
    data class Error<out T>(
        val errorResponse: ErrorResponse
    ) : NetworkResource<T>()
}


