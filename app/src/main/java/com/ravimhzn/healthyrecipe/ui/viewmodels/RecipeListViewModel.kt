package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(private val recipeListRepository: RecipeListRepository) :
    ViewModel() {

    var mIsViewingRecipes = false

    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return recipeListRepository.getRecipe()
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        mIsViewingRecipes = true //for changing the view of recyclerview
        recipeListRepository.searchRecipeApi(query, pageNumber)
    }
}