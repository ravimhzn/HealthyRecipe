package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeIngredientViewModel @Inject constructor(private val recipeListRepository: RecipeListRepository) :
    ViewModel() {

    var mDidRetrieveRecipe = false
    lateinit var mRecipeId: String

    fun getIngredients(): LiveData<Recipe> {
        return recipeListRepository.getRecipeIngredients()
    }

    fun searchForRecipeIngredients(recipeId: String) {
        mRecipeId = recipeId
        recipeListRepository.getRecipeIngredientsFromApi(recipeId)
    }

    fun isRecipeRequestTimedOut(): LiveData<Boolean> {
        return recipeListRepository.isRecipeRequestTimedOut()
    }

    fun setRetriveResult(b: Boolean) {
        mDidRetrieveRecipe = b
    }

}