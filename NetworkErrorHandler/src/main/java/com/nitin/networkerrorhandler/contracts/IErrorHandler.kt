package com.nitin.networkerrorhandler.contracts

import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.datasource.model.TypeError

internal interface IErrorHandler {
    fun showError(errorResponse: ErrorResponse, typeError: TypeError)
}

