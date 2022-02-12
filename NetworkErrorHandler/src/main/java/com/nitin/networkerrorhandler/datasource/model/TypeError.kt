package com.nitin.networkerrorhandler.datasource.model

import android.app.Activity
import android.content.Context

sealed class TypeError {
    data class Toast(val context: Context) : TypeError() // and custom
    data class Snack(val activity: Activity) : TypeError()  // custom and with action
}