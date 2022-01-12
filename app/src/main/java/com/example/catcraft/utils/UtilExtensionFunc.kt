package com.example.catcraft.utils

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.catcraft.R
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImageWithUrl(imgUrl: String?) {
    val brokenImage = R.drawable.ic_broken_image
    Glide.with(this.context)
        .load(imgUrl ?: brokenImage)
        .centerCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(brokenImage)
        )
        .into(this)
}

fun String.snack(color: Int, view: View, duration: Int = Snackbar.LENGTH_LONG) {
    val snackbar = Snackbar.make(view, this, duration)
    snackbar.view.setBackgroundColor(color)
    snackbar.setTextColor(Color.WHITE)
    snackbar.show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

