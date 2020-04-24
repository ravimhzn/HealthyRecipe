package com.ravimhzn.healthyrecipe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.util.Constants
import java.util.*

class RecipeListAdapter(private val mOnRecipeListener: OnRecipeListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //private lateinit var arrRecipeList: MutableList<Recipe>

    private var arrRecipeList: MutableList<Recipe>? = null


    private val RECIPE_TYPE = 1
    private val LOADING_TYPE = 2
    private val CATEGORY_TYPE = 3
    private val EXHAUSTED_TYPE = 4

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View? = null
        return when (viewType) {
            RECIPE_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                RecipeListViewHolder(view, mOnRecipeListener)
            }
            LOADING_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_loading_list_item, parent, false)
                RecipeLoadingViewHolder(view)
            }
            EXHAUSTED_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_search_exhausted, parent, false)
                SearchExhaustedViewHolder(view)
            }
            CATEGORY_TYPE -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_category_list_item, parent, false)
                CategoryViewHolder(view, mOnRecipeListener)
            }
            else -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_recipe_list_item, parent, false)
                RecipeListViewHolder(view, mOnRecipeListener)
            }
        }

    }

    override fun getItemCount(): Int {
        return arrRecipeList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            RECIPE_TYPE -> {
                arrRecipeList?.get(position)?.let { (holder as RecipeListViewHolder).bind(it) }
            }
            CATEGORY_TYPE -> {
                arrRecipeList?.get(position)?.let { (holder as CategoryViewHolder).bind(it) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (arrRecipeList?.get(position)?.social_rank == -1.0) {
            return CATEGORY_TYPE
        } else if (arrRecipeList?.get(position)?.title.equals("LOADING...")) {
            return LOADING_TYPE
        } else if (arrRecipeList?.get(position)?.title.equals("EXHAUSTED...")) {
            return EXHAUSTED_TYPE
        } else {
            return RECIPE_TYPE
        }
    }


    fun setQueryExhausted() {
        hideLoading()
        val exhaustedRecipe = Recipe()
        exhaustedRecipe.title = "EXHAUSTED..."
        arrRecipeList?.add(exhaustedRecipe)
        notifyDataSetChanged()
    }

    private fun hideLoading() {
        if (isLoading()) {
            for (recipe in this!!.arrRecipeList!!) {
                if (recipe.title.equals("LOADING...")) {
                    arrRecipeList?.remove(recipe)
                }
            }
            notifyDataSetChanged()
        }
    }

    /**
     * TODO, USE RESULT CLASS LATER FOR LOADING FUNCTIONALITY
     * THIS IS A VERY DUMB IMPLEMENTATION FOR PRACTICE, plus it reveals business logic
     */
    fun displayLoading() {
        if (!isLoading()) {
            val recipe = Recipe()
            recipe.title = "LOADING..."
            val loadingList: MutableList<Recipe> = ArrayList()
            loadingList.add(recipe)
            arrRecipeList = loadingList
            notifyDataSetChanged()
        }
    }

    private fun isLoading(): Boolean {
        if (arrRecipeList != null) {
            if (arrRecipeList!!.size > 0) {
                if (arrRecipeList!![arrRecipeList!!.size - 1].title.equals("LOADING...")) {
                    return true
                }
            }
        }
        return false
    }

    fun displaySearchCategories() {
        val categories: MutableList<Recipe> = ArrayList()
        for (i in Constants.DEFAULT_SEARCH_CATEGORIES.indices) {
            val recipe = Recipe()
            recipe.title = Constants.DEFAULT_SEARCH_CATEGORIES.get(i)
            recipe.image_url = Constants.DEFAULT_SEARCH_CATEGORY_IMAGES.get(i)
            recipe.social_rank = -1.0
            categories.add(recipe)
        }
        arrRecipeList = categories
        notifyDataSetChanged()
    }


    fun setRecipe(recipeList: MutableList<Recipe>) {
        this.arrRecipeList = recipeList
        notifyDataSetChanged()
    }

    fun getSelectedRecipe(position: Int): Recipe? {
        return arrRecipeList?.get(position)
    }
}