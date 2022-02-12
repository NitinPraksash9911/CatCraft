package com.nitin.networkerrorhandler.errortypes

import android.app.Activity
import android.graphics.Color
import com.nitin.networkerrorhandler.datasource.model.DataStatus
import com.nitin.networkerrorhandler.datasource.model.ErrorResponse
import com.nitin.networkerrorhandler.view.CustomSnackbar

internal object SnackBarError {

    fun showSnackBarError(activity: Activity, errorResponse: ErrorResponse) {


        when (errorResponse.dataStatus) {

            DataStatus.InvalidData -> {

                errorResponse.graphQlError.toString()

                CustomSnackbar(activity)
                    .show {
                        message(
                            errorResponse.exception?.localizedMessage
                                ?: "InvalidData: " + " response code:" + errorResponse.responseCode
                        )
                        backgroundColor(Color.BLACK)
                    }
            }


            DataStatus.HTTPError -> {
                CustomSnackbar(activity)
                    .show {
                        backgroundColor(Color.BLACK)
                        message(
                            "HTTPError: " + errorResponse.responseErrorBody?.string() + " response code:" + errorResponse.responseCode
                        )
                    }
            }


            DataStatus.GotException -> {
                CustomSnackbar(activity)
                    .show {
                        message(
                            "GotException: " + errorResponse.exception?.localizedMessage
                        )
                        backgroundColor(Color.BLACK)

                        duration(2000)
                        withAction("Test") {
                            ToastError.showErrorToast(activity, errorResponse)
                        }
                    }
            }

        }
    }
}