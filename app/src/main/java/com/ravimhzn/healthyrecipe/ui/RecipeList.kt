package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.ui.adapter.OnRecipeListener
import com.ravimhzn.healthyrecipe.ui.adapter.RecipeListAdapter
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeListViewModel
import com.ravimhzn.healthyrecipe.util.VerticalSpacingItemDecorator
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
        val itemDecorator = VerticalSpacingItemDecorator(30)
        recyclerView.addItemDecoration(itemDecorator)
        recyclerView.adapter = recyclerAdapter
        if (!viewModel.mIsViewingRecipes) {
            displaySearchCategories()
        }
        recyclerView.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1)) {
                    // search the next page
                    viewModel.searchNextPage()
                }
            }
        })
    }

    private fun initSearchView() {
        searchView.setOnQueryTextListener(object :
            OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerAdapter.displayLoading()
                query?.let { viewModel.searchRecipeApi(it, 1) }
                /**
                 * NOTE:: clears the focus from SearchView, comes in handy while doing backpress
                 * while making a network request for recipeList
                 */
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
            viewModel.mIsPerformingQuery = false
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

    override fun onBackPressed() {
        if (viewModel.onBackPressed()) {
            super.onBackPressed()
        } else {
            displaySearchCategories()
        }
    }
}
