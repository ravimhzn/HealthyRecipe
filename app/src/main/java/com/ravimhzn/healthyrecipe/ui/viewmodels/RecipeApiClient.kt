package com.ravimhzn.healthyrecipe.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ravimhzn.healthyrecipe.AppExecutors
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.model.RecipeSearchReponse
import com.ravimhzn.healthyrecipe.network.RecipeApiService
import com.ravimhzn.healthyrecipe.util.Constants.Companion.NETWORK_TIMEOUT
import retrofit2.Call
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RecipeApiClient @Inject constructor(
    private val recipeApiService: RecipeApiService,
    private val appExecutors: AppExecutors
) {
    private val TAG = RecipeApiClient::class.java.name
    var mRecipeLiveData: MutableLiveData<MutableList<Recipe>> = MutableLiveData<MutableList<Recipe>>()
    lateinit var mRetrieveRecipeRunnable: RetrieveRecipeRunnable

    fun searchRecipeApi(
        query: String,
        pageNumber: Int
    ) {
        mRetrieveRecipeRunnable = RetrieveRecipeRunnable(query, pageNumber)
        var handler = appExecutors.networkIOExecutors.submit(
            mRetrieveRecipeRunnable
        )

        appExecutors.networkIOExecutors.schedule(
            {
                handler.cancel(true) //let the user know its timed out
            },
            NETWORK_TIMEOUT.toLong(),
            TimeUnit.MILLISECONDS
        )
    }

    inner class RetrieveRecipeRunnable(
        private val query: String,
        private val pageNumber: Int,
        private var cancelRequest: Boolean = false
    ) : Runnable {
        override fun run() {
            var response = getRecipes(query, pageNumber).execute()
            if (cancelRequest) {
                return
            }
            if (response.code() == 200) {
                var list = response.body()?.recipes
                if (pageNumber == 1) {
                    mRecipeLiveData.postValue(list as MutableList<Recipe>)
                } else {
                    var currentRecipe = mRecipeLiveData.value
                    currentRecipe?.addAll(list as MutableList<Recipe>)
                    mRecipeLiveData.postValue(currentRecipe)
                }
            } else {
                var error = response.errorBody().toString()
                Log.d(TAG, "ERROR -> $error")
                mRecipeLiveData.postValue(null)
            }
        }

        //gets the recipe from server
        fun getRecipes(query: String, pageNumber: Int): Call<RecipeSearchReponse> {
            return recipeApiService.searchRecipe(query, pageNumber.toString())
        }

        fun cancelRequest() {
            Log.d(TAG, "Cancel Request -> Cancelling the search request")
            cancelRequest = true
        }
    }
}
