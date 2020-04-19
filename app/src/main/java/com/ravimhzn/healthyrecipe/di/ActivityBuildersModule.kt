package com.ravimhzn.healthyrecipe.di


import com.ravimhzn.healthyrecipe.ui.RecipeIngredient
import com.ravimhzn.healthyrecipe.ui.RecipeList
import com.ravimhzn.healthyrecipe.ui.di.MainFragmentBuildersModule
import com.ravimhzn.healthyrecipe.ui.di.MainViewModelModule
import com.ravimhzn.healthyrecipe.ui.di.ingredients.IngredientsModule
import com.ravimhzn.healthyrecipe.ui.di.ingredients.IngredientsViewModelModule
import com.ravimhzn.infosyscodingapplication.ui.di.MainModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class,
            MainModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeRecipeList(): RecipeList

    @ContributesAndroidInjector(
        modules = [
            IngredientsModule::class,
            IngredientsViewModelModule::class
        ]
    )
    abstract fun contributeRecipeIngredient(): RecipeIngredient
}
