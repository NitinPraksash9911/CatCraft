package com.example.catcraft.arch

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    object InvalidData : Resource<Nothing>()
    data class NetworkException(val error: String) : Resource<Nothing>()
    data class Error(val error: String) : Resource<Nothing>()

    sealed class HttpErrors(val msg: String) : Resource<Nothing>() {
        data class ResourceForbidden(val exception: String) : HttpErrors(exception)
        data class ResourceNotFound(val exception: String) : HttpErrors(exception)
        data class InternalServerError(val exception: String) : HttpErrors(exception)
        data class BadGateWay(val exception: String) : HttpErrors(exception)
        data class ResourceRemoved(val exception: String) : HttpErrors(exception)
        data class RemovedResourceFound(val exception: String) : HttpErrors(exception)
    }

}