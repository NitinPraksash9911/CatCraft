package com.jumpingminds.networkrequesthandler.datasource.model

import okhttp3.ResponseBody


/**

@author: Nitin Prakash
@date: 12-Feb-2022

This class represent the type of error, error body from retrofit or graphQl so we can easily parse the error on client side

 */
data class ErrorResponse(
    val responseCode: Int? = 0,
    val retrofitErrorResponse: ResponseBody? = null,
    val errorStatus: ErrorStatus,
    val graphQlErrorResponse: GraphQlError? = null,
    val exception: Throwable? = null,
)