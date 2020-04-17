package com.ravimhzn.healthyrecipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var __v: Int? = null,
    var _id: String? = null,
    var image_url: String? = null,
    var ingredients: List<String?>? = null,
    var publisher: String? = null,
    var publisher_url: String? = null,
    var recipe_id: String? = null,
    var social_rank: Double? = null,
    var source_url: String? = null,
    var title: String? = null
) : Parcelable