package com.ravimhzn.healthyrecipe.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    val __v: Int? = null,
    val _id: String? = null,
    val image_url: String? = null,
    val ingredients: List<String?>? = null,
    val publisher: String? = null,
    val publisher_url: String? = null,
    val recipe_id: String? = null,
    val social_rank: Double? = null,
    val source_url: String? = null,
    val title: String? = null
) : Parcelable