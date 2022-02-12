package com.nitin.networkerrorhandler.errortypes

import android.content.Context
import com.nitin.networkerrorhandler.datasource.model.DataStatus
import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.utils.toast

internal object ToastError {


    fun showErrorToast(context: Context, errorResponse: ErrorResponse) {


        when (errorResponse.dataStatus) {

            DataStatus.InvalidData -> {

                context.toast(
                    errorResponse.exception?.localizedMessage
                        ?: "InvalidData " + " response code:" + errorResponse.responseCode
                )
            }
            DataStatus.HTTPError -> {

                context.toast("HTTP Error " + errorResponse.responseErrorBody?.string() + " response code:" + errorResponse.responseCode)

            }
            DataStatus.GotException -> {
                context.toast(
                    "GotException: " + errorResponse.exception?.localizedMessage
                )
            }
        }

    }


}