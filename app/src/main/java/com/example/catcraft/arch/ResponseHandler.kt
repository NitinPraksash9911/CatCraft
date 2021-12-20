package com.example.catcraft.arch

import com.example.catcraft.arch.Resource.Success
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = call()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) return Success(
                body
            )
        }

        Resource.Error(response.message())

    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> {
                Resource.Error(throwable.localizedMessage ?: "An IO exception happened ")
            }
            is HttpException -> {

                Resource.Error(throwable.message ?: "An Http exception happened ")
            }
            else -> {
                Resource.Error(throwable.message ?: "An Unknown exception happened ")
            }
        }
    }
}
