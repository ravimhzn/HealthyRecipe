package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RecipeList : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RecipeListViewModel> { viewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        buttonTest.setOnClickListener {
            viewModel.serachRecipeApi("breakfast", 1)
        }

    }

    private fun subscribeObservers() {
        viewModel.getRecipe().observe(this, Observer {
            for (a in it) {
                println("debug -> ${a.publisher}")
            }
        })
    }
}
