package com.jumpingminds.networkrequesthandler.presenter

import android.app.Activity
import android.content.Context
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.jumpingminds.networkrequesthandler.R
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorResponse
import com.jumpingminds.networkrequesthandler.datasource.model.ErrorStatus
import com.jumpingminds.networkrequesthandler.datasource.model.TypeError
import com.jumpingminds.networkrequesthandler.utils.loadImageWithUrl
import com.jumpingminds.networkrequesthandler.view.NPSnackBar

internal object ProduceError {

    fun showNetworkError(activity: Activity, errorResponse: ErrorResponse) {

        when (errorResponse.errorStatus) {

            /*---------------------------Server Errors-----------------------------------*/

            ErrorStatus.ServerError, ErrorStatus.InvalidError -> {
                displaySnackBar(
                    context = activity,
                    errorTitle = errorResponse.exception?.message ?: "Wait a minute",
                    errorMsg = errorResponse.retrofitErrorResponse.toString()
                        ?: "And do one minute mediation",
                    typeError = errorResponse.typeError,
                    errorImageUrl = "",
                    isAllowToErrorOnUI = true
                )
            }

            /*---------------------------Exceptions-----------------------------------*/

            ErrorStatus.HTTPError -> {
                displaySnackBar(
                    context = activity,
                    errorTitle = "Just Be Patience",
                    errorMsg = errorResponse.exception?.localizedMessage,
                    typeError = errorResponse.typeError,
                    errorImageUrl = null
                )
            }

            ErrorStatus.NetworkError -> {
                displaySnackBar(
                    context = activity,
                    errorTitle = "Just Be Patience",
                    errorMsg = "NE: " + errorResponse.exception?.localizedMessage,
                    typeError = errorResponse.typeError,
                    errorImageUrl = null
                )
            }

            ErrorStatus.GotException -> {
                displaySnackBar(
                    context = activity,
                    errorTitle = "Just Be Patience",
                    errorMsg = "GE: " + errorResponse.exception?.localizedMessage,
                    typeError = errorResponse.typeError,
                    errorImageUrl = null
                )

            }
        }
    }

    private fun displaySnackBar(
        context: Context,
        errorTitle: String,
        errorMsg: String? = "Stay connected with us",
        errorImageUrl: String?,
        typeError: TypeError,
        duration: Int = BaseTransientBottomBar.LENGTH_LONG,
        isAllowToErrorOnUI: Boolean = true
    ) {

        if (isAllowToErrorOnUI.not()) {
            return
        }

        when (typeError) {
            TypeError.Snack -> {
                NPSnackBar(context)
                    .show {
                        duration(duration)
                        customView(R.layout.snack_error_layout)
                        withCustomView { snackLayout ->

                            snackLayout.apply {

                                //error title
                                findViewById<TextView>(R.id.error_title_tv).text = errorTitle

                                //error message
                                findViewById<TextView>(R.id.error_msg_tv).text = errorMsg

                                //LoadImage
                                findViewById<ImageButton>(R.id.error_emoji_iv).loadImageWithUrl(
                                    imgUrl = errorImageUrl,
                                    defaultImageId = R.drawable.smiling_face
                                )

                                //dismiss onclick
                                findViewById<ImageButton>(R.id.cancel_button).setOnClickListener {
                                    dismiss()
                                }
                            }

                        }
                    }
            }
            TypeError.Toast -> {
                NPSnackBar(context)
                    .show {
                        duration(duration)
                        customView(R.layout.toast_error_layout)
                        withCustomView { toastLayout ->

                            toastLayout.apply {

                                //error title
                                findViewById<TextView>(R.id.error_title_tv).text = errorTitle

                                //error message
                                findViewById<TextView>(R.id.error_msg_tv).text = errorMsg

                                //dismiss onclick
                                findViewById<ImageButton>(R.id.cancel_button).setOnClickListener {
                                    dismiss()
                                }
                            }

                        }
                    }
            }
        }

    }
}

