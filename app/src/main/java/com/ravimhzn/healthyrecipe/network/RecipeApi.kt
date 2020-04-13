package com.ravimhzn.healthyrecipe.network

import com.ravimhzn.healthyrecipe.model.RecipeResponse
import com.ravimhzn.healthyrecipe.model.RecipeSearchListReponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RecipeApi {

    @GET("api/search")
    fun searchRecipe(
        @Query("q") query: String?,
        @Query("page") page: String?
    ): Call<RecipeSearchListReponse>


    @GET("api/get")
    fun getRecipe(
        @Query("rId") recipe_id: String?
    ): Call<RecipeResponse>
}