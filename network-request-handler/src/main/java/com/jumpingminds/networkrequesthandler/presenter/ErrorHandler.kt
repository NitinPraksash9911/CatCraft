package com.jumpingminds.networkrequesthandler.presenter

import android.app.Activity
import com.jumpingminds.networkrequesthandler.contracts.IErrorHandler
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse


//TODO

/* 1. Check with how to show different snackbar with different colors as defined in figma
*  2.  custom toast with layout is deprecated in Android 11 so we will use custom snackbar instead of toast
* */
object ErrorHandler : IErrorHandler {

    override fun showError(errorResponse: ErrorResponse, activity: Activity) {

        ProduceError.showNetworkError(activity, errorResponse)

    }
}