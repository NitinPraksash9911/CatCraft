package com.nitin.networkerrorhandler.datasource.model

import android.app.Activity
import android.content.Context

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error<T>(
        val errorResponse: ErrorResponse
    ) : Resource<T>()
}


