package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.ui.adapter.OnRecipeListener
import com.ravimhzn.healthyrecipe.ui.adapter.RecipeListAdapter
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class RecipeList : BaseActivity(), OnRecipeListener {

    private val TAG = RecipeList::class.java.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RecipeListViewModel> { viewModelFactory }


    lateinit var recyclerAdapter: RecipeListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initSearchView()
        subscribeObservers()
    }

    private fun initViews() {
        recyclerAdapter = RecipeListAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = recyclerAdapter
        if (!viewModel.mIsViewingRecipes) {
            displaySearchCategories()
        }
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerAdapter.displayLoading()
                query?.let { viewModel.searchRecipeApi(it, 1) }
                searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun subscribeObservers() {
        viewModel.getRecipe().observe(this, Observer {
            setRecyclerAdapter(it)
        })
    }

    private fun setRecyclerAdapter(it: MutableList<Recipe>?) {
        it?.let { it1 -> recyclerAdapter.setRecipe(it1) }
    }

    override fun onRecipeClick(position: Int) {
        Log.d(TAG, "POSITION -> $position")
    }

    override fun onCategoryClick(category: String) {
        recyclerAdapter.displayLoading()
        category?.let { viewModel.searchRecipeApi(it, 1) }
    }

    private fun displaySearchCategories() {
        viewModel.mIsViewingRecipes = false
        recyclerAdapter.displaySearchCategories()
    }
}
