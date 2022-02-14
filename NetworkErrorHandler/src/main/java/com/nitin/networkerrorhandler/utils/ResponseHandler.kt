package com.nitin.networkerrorhandler.utils

import com.nitin.networkerrorhandler.datasource.model.DataStatus
import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.datasource.model.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {

    return try {
        val response = call()
        when {
            response.isSuccessful -> {
                val body = response.body()
                if (body != null) {
                    Resource.Success(body)
                } else {
                    Resource.Error(
                        ErrorResponse(
                            responseCode = response.code(),
                            responseErrorBody = response.errorBody(),
                            dataStatus = DataStatus.ServerError
                        )
                    )
                }
            }
            else -> {
                Resource.Error(
                    ErrorResponse(
                        responseCode = response.code(),
                        responseErrorBody = response.errorBody(),
                        dataStatus = DataStatus.InvalidData
                    )
                )
            }
        }
    } catch (throwable: Throwable) {

        when (throwable) {
            is IOException -> {
                Resource.Error(
                    ErrorResponse(
                        exception = throwable,
                        dataStatus = DataStatus.NetworkError
                    )
                )
            }
            is HttpException -> {
                Resource.Error(
                    ErrorResponse(
                        exception = throwable,
                        dataStatus = DataStatus.HTTPError
                    )
                )
            }
            else -> {
                Resource.Error(
                    ErrorResponse(
                        exception = throwable,
                        dataStatus = DataStatus.GotException
                    )
                )
            }
        }

    }
}

//suspend fun <T> executeGraphQlAPi(call: suspend () -> com.apollographql.apollo.api.Response<T>): Resource<T> {
//    return try {
//        val response = call()
//        when {
//            response.data != null && response.hasErrors().not() -> {
//                Resource.Success(response.data!!)
//            }
//            response.hasErrors() -> {
//                Resource.Error(
//                    ErrorResponse(
//                        responseCode = 101,
//                        responseErrorBody = null,
//                        dataStatus = DataStatus.ServerError,
//                        graphQlError = Gson().fromJson(
//                            response.errors?.firstOrNull()?.toString(),
//                            Error::class.java
//                        )
//                    )
//                )
//            }
//            else -> {
//                Resource.Error(
//                    ErrorResponse(
//                        responseCode = 101,
//                        responseErrorBody = null,
//                        dataStatus = DataStatus.InvalidData,
//                        graphQlError = Gson().fromJson(
//                            response.errors?.firstOrNull()?.toString(),
//                            Error::class.java
//                        )
//                    )
//                )
//            }
//        }
//    } catch (throwable: Exception) {
//        when (throwable) {
//            is IOException -> {
//                Resource.Error(
//                    ErrorResponse(
//                        exception = throwable,
//                        dataStatus = DataStatus.NetworkError
//                    )
//                )
//            }
//            is HttpException -> {
//                Resource.Error(
//                    ErrorResponse(
//                        exception = throwable,
//                        dataStatus = DataStatus.HTTPError
//                    )
//                )
//            }
//            else -> {
//                Resource.Error(
//                    ErrorResponse(
//                        exception = throwable,
//                        dataStatus = DataStatus.GotException
//                    )
//                )
//            }
//        }
//    }
//}


/*
1- >InvalidData// mean request is success but error from server
2-> HTTPError // mean request has some http error like above
3-> Exception // due to
4-> Successful // range code in 200..299

*/
/*NOTE:
*
2xx: Success – Indicates that the client’s request was accepted successfully.
3xx: Redirection – Indicates that the client must take some additional action in order to complete their request.
4xx: Client Error – This category of error status codes points the finger at clients.
5xx: Server Error – The server takes responsibility for these error status codes.
*
* */




