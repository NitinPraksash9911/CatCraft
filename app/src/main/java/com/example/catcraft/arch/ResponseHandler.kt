package com.example.catcraft.arch

import com.example.catcraft.arch.Resource.Success
import retrofit2.Response

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

    } catch (e: Exception) {
        Resource.Error(e.localizedMessage ?: "Unexpected Error Occurred")
    }
}
