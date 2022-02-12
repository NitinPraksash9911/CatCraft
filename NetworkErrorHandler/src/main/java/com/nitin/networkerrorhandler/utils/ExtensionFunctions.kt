package com.nitin.networkerrorhandler.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

internal
/**
 * Extension method to show toast for Context.
 */
fun Context?.toast(text: String, duration: Int = Toast.LENGTH_LONG) =
    this?.let { Toast.makeText(it, text, duration).show() }

inline fun View.snack(message: String, @BaseTransientBottomBar.Duration length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) = snack(message, length) {}
