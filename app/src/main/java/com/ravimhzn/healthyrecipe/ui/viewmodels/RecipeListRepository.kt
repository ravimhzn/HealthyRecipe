package com.ravimhzn.healthyrecipe.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ravimhzn.healthyrecipe.model.Recipe
import javax.inject.Inject

class RecipeListRepository @Inject constructor(private val recepieApiClient: RecipeApiClient) {

    private lateinit var mQuery: String
    private var mPageNumber = 0
    var mIsQueryExhausted: MutableLiveData<Boolean> = MutableLiveData()
    var mRecipesMediator: MediatorLiveData<MutableList<Recipe>> = MediatorLiveData<MutableList<Recipe>>()

    init {
        initMediatorLiveDataForRecipes()
    }

    private fun initMediatorLiveDataForRecipes() {
        var result = recepieApiClient.mRecipeLiveData
        mRecipesMediator.addSource(result, Observer {
            if (it != null) {
                mRecipesMediator.value = it
                doneQuery(it)
            } else {
                //Search Database Cache
                doneQuery(null)
            }
        })
    }

    private fun doneQuery(list: List<Recipe>?) {
        if (list != null) {
            if (list.size % 30 != 0) {
                mIsQueryExhausted.value = true
            }
        } else {
            mIsQueryExhausted.setValue(true)
        }
    }

    fun getRecipe(): LiveData<MutableList<Recipe>> {
        return mRecipesMediator
    }

    fun getRecipeIngredients(): LiveData<Recipe> {
        return recepieApiClient.mRecipeLiveDataIngredients
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

    fun getRecipeIngredientsFromApi(recipeId: String) {
        recepieApiClient.getRecipeIngredientsFromApi(recipeId)
    }

    fun cancelRequest() {
        recepieApiClient.cancelRequest()
    }

    fun isRecipeRequestTimedOut(): LiveData<Boolean> {
        return recepieApiClient.mIsNetworkTimeout
    }
}