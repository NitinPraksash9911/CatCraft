package com.jumpingminds.networkrequesthandler.utils

import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.exception.ApolloException
import com.apollographql.apollo3.exception.ApolloHttpException
import com.apollographql.apollo3.exception.ApolloNetworkException
import com.google.gson.Gson
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorStatus
import com.jumpingminds.networkrequesthandler.datasource.model.GraphQlError
import com.jumpingminds.networkrequesthandler.datasource.model.NetworkResource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> executeRetrofitApi(call: suspend () -> Response<T>): NetworkResource<T> {

    return try {
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
                            errorStatus = ErrorStatus.ServerError
                        )
                    )
                }
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        responseCode = response.code(),
                        retrofitErrorResponse = response.errorBody(),
                        errorStatus = ErrorStatus.InvalidError
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
                        errorStatus = ErrorStatus.NetworkError
                    )
                )
            }
            is HttpException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.HTTPError
                    )
                )
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.GotException
                    )
                )
            }
        }

    }
}

suspend fun <T : Operation.Data> executeGraphQlApi(call: suspend () -> com.apollographql.apollo3.api.ApolloResponse<T>): NetworkResource<T> {
    return try {
        val response = call()
        when {
            response.data != null && response.hasErrors().not() -> {
                NetworkResource.Success(response.data!!)
            }
            response.hasErrors() -> {
                NetworkResource.Error(
                    ErrorResponse(
                        responseCode = 101,
                        retrofitErrorResponse = null,
                        errorStatus = ErrorStatus.ServerError,
                        graphQlErrorResponse = GraphQlError.from(
                            Gson(),
                            response.errors?.firstOrNull()?.nonStandardFields
                        )
                    )
                )
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        responseCode = 101,
                        retrofitErrorResponse = null,
                        errorStatus = ErrorStatus.InvalidError,
                        graphQlErrorResponse = GraphQlError.from(
                            Gson(),
                            response.errors?.firstOrNull()?.nonStandardFields
                        )
                    )
                )
            }
        }
    } catch (throwable: ApolloException) {
        when (throwable) {
            is ApolloNetworkException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.NetworkError
                    )
                )
            }
            is ApolloHttpException -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.HTTPError
                    )
                )
            }
            else -> {
                NetworkResource.Error(
                    ErrorResponse(
                        exception = throwable,
                        errorStatus = ErrorStatus.GotException
                    )
                )
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




