package com.ravimhzn.healthyrecipe.extension

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ravimhzn.healthyrecipe.R

/**
 * Load image from URL via Glide.
 */
fun ImageView.setImageUrl(url: String?, radius: Int = 0) {
    Glide.with(context).load(url).apply {
        if (radius != 0) {
            apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(radius)
                )
            )
        }
    }.into(this)
}

/**
 * Load image from drawables via Glide.
 */
fun ImageView.setImageUri(id: Uri, radius: Int = 0) {
    Glide.with(context).load(id).placeholder(R.drawable.ic_launcher_background).apply {
        if (radius != 0) {
            apply(
                RequestOptions.bitmapTransform(
                    RoundedCorners(radius)
                )
            )
        }
    }.into(this)
}