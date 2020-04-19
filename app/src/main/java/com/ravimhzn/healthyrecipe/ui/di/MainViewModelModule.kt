package com.ravimhzn.healthyrecipe.ui.di

import androidx.lifecycle.ViewModel
import com.ravimhzn.healthyrecipe.di.ViewModelKey
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeIngredientViewModel
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RecipeListViewModel::class)
    abstract fun bindRecipeListViewModel(recipeListViewModel: RecipeListViewModel): ViewModel
}