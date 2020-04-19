package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListRepository @Inject constructor(private val recepieApiClient: RecipeApiClient) {

    private lateinit var mQuery: String
    private var mPageNumber = 0
    lateinit var mIsQueryExhausted: MutableLiveData<Boolean>


    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return recepieApiClient.mRecipeLiveData
    }

    fun searchRecipeApi(query: String, pageNumber: Int) {
        var pNum = pageNumber
        if (pNum == 0) {
            pNum = 1
        }
        mQuery = query
        mPageNumber = pageNumber
        recepieApiClient.searchRecipeApi(query, pNum)
    }

    fun searchNextPage() {
        recepieApiClient.searchRecipeApi(mQuery, mPageNumber++)
    }

    fun cancelRequest() {
        recepieApiClient.cancelRequest()
    }
}