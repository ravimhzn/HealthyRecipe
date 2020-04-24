package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListViewModel @Inject constructor(private val recipeListRepository: RecipeListRepository) :
    ViewModel() {

    var mIsViewingRecipes = false
    var mIsPerformingQuery = false


    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return recipeListRepository.getRecipe()
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        mIsViewingRecipes = true //for changing the view of recyclerview
        mIsPerformingQuery = true
        recipeListRepository.searchRecipeApi(query, pageNumber)
    }

    fun onBackPressed(): Boolean {
        if (mIsPerformingQuery) {
            // cancel the query if user presses back button while searching for recipe list. TODO() Change this implementation with fragmetns later.
            recipeListRepository.cancelRequest()
            mIsPerformingQuery = false
        }
        if (mIsViewingRecipes) {
            mIsViewingRecipes = false
            return false
        }
        return true
    }


    fun searchNextPage() {
        if (!mIsPerformingQuery
            && mIsViewingRecipes
            && !isQueryExhausted().value!!
        ) {
            recipeListRepository.searchNextPage()
        }
    }


    fun isQueryExhausted(): LiveData<Boolean> {
        return recipeListRepository.mIsQueryExhausted
    }
}