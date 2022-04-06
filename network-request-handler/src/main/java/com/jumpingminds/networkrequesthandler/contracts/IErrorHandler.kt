package com.jumpingminds.networkrequesthandler.contracts

import android.app.Activity
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse

internal interface IErrorHandler {

    fun showError(errorResponse: ErrorResponse, activity: Activity)
}

