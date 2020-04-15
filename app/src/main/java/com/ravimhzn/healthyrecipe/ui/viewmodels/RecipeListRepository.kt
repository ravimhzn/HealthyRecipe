package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListRepository @Inject constructor(private val recepieApiClient: RecipeApiClient) {

    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return recepieApiClient.mRecipeLiveData
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        var pNum = pageNumber
        if (pNum == 0) {
            pNum = 1
        }
        recepieApiClient.searchRecipeApi(query, pNum)
    }
}