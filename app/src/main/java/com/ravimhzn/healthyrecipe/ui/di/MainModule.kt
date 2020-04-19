package com.ravimhzn.infosyscodingapplication.ui.di


import android.content.Context
import com.ravimhzn.healthyrecipe.AppExecutors
import com.ravimhzn.healthyrecipe.network.RecipeApiService
import com.ravimhzn.healthyrecipe.ui.adapter.RecipeListAdapter
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeApiClient
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class MainModule {

    /**
     * We don't have to declare @Singleton here since we are using scope binding.
     * It's already declared @Singleton in AppModule
     * This class references scoped bindings
     */
//    @Provides
//    fun provideApiService(retrofit: Retrofit): RecipeApiService {
//        return retrofit.create(RecipeApiService::class.java)
//    }

//    @Provides
//    fun provideNetworkExecutors(): AppExecutors {
//        return AppExecutors()
//    }
//
//    @Provides
//    fun provideRecipeApiClient(
//        appExecutors: AppExecutors,
//        recipeApiService: RecipeApiService
//    ): RecipeApiClient {
//        return RecipeApiClient(recipeApiService, appExecutors)
//    }
//
//    @Provides
//    fun provideRecipeListRepository(recipeApiClient: RecipeApiClient): RecipeListRepository {
//        return RecipeListRepository(recipeApiClient)
//    }
//
//    @Provides
//    fun provideRecipeListAdapter(): RecipeListAdapter {
//        return RecipeListAdapter()
//    }
}