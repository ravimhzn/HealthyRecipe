package com.ravimhzn.healthyrecipe.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

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