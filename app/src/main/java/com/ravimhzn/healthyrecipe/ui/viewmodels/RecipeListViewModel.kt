package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(private val recipeListRepository: RecipeListRepository) :
    ViewModel() {

    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return recipeListRepository.getRecipe()
    }

    fun serachRecipeApi(query: String, pageNumber: Int) {
        recipeListRepository.searchRecipeApi(query, pageNumber)
    }
}