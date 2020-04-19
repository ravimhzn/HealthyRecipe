package com.ravimhzn.healthyrecipe.ui.di.ingredients

import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.di.ViewModelKey
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeIngredientViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class IngredientsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeIngredientViewModel::class)
    abstract fun bindRecipeIngredientViewModel(recipeIngredientViewModel: RecipeIngredientViewModel): ViewModel
}