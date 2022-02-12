package com.nitin.networkerrorhandler.errortypes

import com.nitin.networkerrorhandler.contracts.IErrorHandler
import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.datasource.model.TypeError

object ErrorHandler : IErrorHandler {

    override fun showError(errorResponse: ErrorResponse, typeError: TypeError) {

        when (typeError) {

            is TypeError.Toast -> {

                ToastError.showErrorToast(typeError.context, errorResponse)
            }

            is TypeError.Snack -> {

                SnackBarError.showSnackBarError(typeError.activity, errorResponse)
            }
        }

    }
}