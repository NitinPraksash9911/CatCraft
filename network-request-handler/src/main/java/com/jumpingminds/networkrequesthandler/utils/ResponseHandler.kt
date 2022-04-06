package com.jumpingminds.networkrequesthandler.utils

import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorStatus
import com.jumpingminds.networkrequesthandler.datasource.model.NetworkResource
import com.jumpingminds.networkrequesthandler.datasource.model.TypeError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> executeRetrofitApi(call: suspend () -> Response<T>): NetworkResource<T> {

    return withContext(Dispatchers.IO) {
        try {
            val response = call()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    if (body != null) {
                        NetworkResource.Success(body)
                    } else {
                        NetworkResource.Error(
                            ErrorResponse(
                                responseCode = response.code(),
                                retrofitErrorResponse = response.errorBody(),
                                errorStatus = ErrorStatus.ServerError,
                                typeError = TypeError.Snack
                            )
                        )
                    }
                }
                else -> {
                    NetworkResource.Error(
                        ErrorResponse(
                            responseCode = response.code(),
                            retrofitErrorResponse = response.errorBody(),
                            errorStatus = ErrorStatus.InvalidError,
                            typeError = TypeError.Snack
                        )
                    )
                }
            }
        } catch (throwable: Throwable) {

            when (throwable) {
                is IOException -> {
                    NetworkResource.Error(
                        ErrorResponse(
                            exception = throwable,
                            errorStatus = ErrorStatus.NetworkError,
                            typeError = TypeError.Toast
                        )
                    )
                }
                is HttpException -> {
                    NetworkResource.Error(
                        ErrorResponse(
                            exception = throwable,
                            errorStatus = ErrorStatus.HTTPError,
                            typeError = TypeError.Snack
                        )
                    )
                }
                else -> {
                    NetworkResource.Error(
                        ErrorResponse(
                            exception = throwable,
                            errorStatus = ErrorStatus.GotException,
                            typeError = TypeError.Snack
                        )
                    )
                }
            }

        }
    }

}


/*
NOTE:

2xx: Success – Indicates that the client’s request was accepted successfully.
3xx: Redirection – Indicates that the client must take some additional action in order to complete their request.
4xx: Client Error – This category of error status codes points the finger at clients.
5xx: Server Error – The server takes responsibility for these error status codes.

*/




