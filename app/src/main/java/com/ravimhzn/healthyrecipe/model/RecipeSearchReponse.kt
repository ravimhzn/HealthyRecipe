package com.ravimhzn.healthyrecipe.model

data class RecipeSearchReponse(
    val count: Int? = null,
    val recipes: MutableList<Recipe?>? = null
)