package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.ui.adapter.RecipeListAdapter
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RecipeList : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RecipeListViewModel> { viewModelFactory }

    @Inject
    lateinit var recyclerAdapter: RecipeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        subscribeObservers()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
    }

    private fun subscribeObservers() {
        viewModel.getRecipe().observe(this, Observer {
            setRecyclerAdapter(it)
        })
    }

    private fun setRecyclerAdapter(it: MutableList<Recipe>?) {
        it?.let { it1 -> recyclerAdapter.setRecipe(it1) }
    }
}
