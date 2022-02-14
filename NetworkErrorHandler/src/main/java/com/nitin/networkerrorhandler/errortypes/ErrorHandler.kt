package com.nitin.networkerrorhandler.errortypes

import com.nitin.networkerrorhandler.contracts.IErrorHandler
import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.datasource.model.TypeError


//TODO

/*
*  1. we will use only showError(ErrorResponse,context) for graph ql api error  handling
*  2. We will check which type of error we want show on the basis of error response
*  3. we will use the error response value?:default
*  4. Decide how use type of error in error response because it will come as string and if it is null then we will show snack bar
*
* */
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