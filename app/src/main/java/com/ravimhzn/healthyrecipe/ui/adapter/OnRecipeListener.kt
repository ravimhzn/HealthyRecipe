package com.ravimhzn.healthyrecipe.ui.adapter

interface OnRecipeListener {

    fun onRecipeClick(position: Int)

    fun onCategoryClick(category: String)
}