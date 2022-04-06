package com.jumpingminds.networkrequesthandler.utils

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jumpingminds.networkrequesthandler.R


fun ImageView.loadImageWithUrl(imgUrl: String?, @DrawableRes defaultImageId: Int) {
    Glide.with(this.context)
        .load(imgUrl ?: defaultImageId)
        .centerCrop()
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(defaultImageId)
        )
        .into(this)
}