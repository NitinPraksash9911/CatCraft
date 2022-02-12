package com.nitin.networkerrorhandler.datasource.model

import okhttp3.ResponseBody

data class ErrorResponse(
    val responseCode: Int? = 0,
    val responseErrorBody: ResponseBody? = null,
    val graphQlError: Error? = null,
    val dataStatus: DataStatus,
    val exception: Exception? = null,
)