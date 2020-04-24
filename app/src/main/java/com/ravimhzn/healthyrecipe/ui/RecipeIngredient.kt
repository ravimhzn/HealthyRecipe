package com.ravimhzn.healthyrecipe.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.extension.setImageUrl
import com.ravimhzn.healthyrecipe.model.Recipe
import com.ravimhzn.healthyrecipe.ui.viewmodels.RecipeIngredientViewModel
import kotlinx.android.synthetic.main.activity_recipe_ingredient.*
import javax.inject.Inject
import kotlin.math.roundToInt

class RecipeIngredient : BaseActivity() {

    private val TAG = RecipeIngredient::class.java.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RecipeIngredientViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_ingredient)
        subscribeObserver()
        getIncomingIntent()
        showProgressBar(true)
    }

    private fun subscribeObserver() {
        viewModel.getIngredients().observe(this, Observer {

//            if (it != null) {
//                if (recipe.getRecipe_id().equals(mRecipeViewModel.getRecipeId())) {
//                    setRecipeProperties(recipe)
//                    mRecipeViewModel.setRetrievedRecipe(true)
//                }
//            }


            if (it.recipe_id.equals(viewModel.mRecipeId)) {
                setUpViews(it)
                viewModel.setRetriveResult(true)
            }


        })
        viewModel.isRecipeRequestTimedOut().observe(this, Observer {
            Log.d(TAG, "debug -> $it")
            Log.d(TAG, "debug -> DidRetrieveRecipe:: ${viewModel.mDidRetrieveRecipe}")
            if (it && !viewModel.mDidRetrieveRecipe) {
                Log.d(TAG, "debug -> TIMEOUT OCCURRED")
                displayErrorScreen("Error Retrieving Data. Please Check Network Connection.")
            }
        })
    }

    private fun setUpViews(it: Recipe?) {
        recipeImage.setImageUrl(it?.image_url)
        recipeTitle.text = it?.title
        recipeSocialScore.text = it?.social_rank?.let { it1 -> it1.roundToInt().toString() }
        ingredientsContainer.removeAllViews()
        var list = it?.ingredients
        if (list != null) {
            for (i in list) {
                val textView = TextView(this)
                textView.text = i
                textView.textSize = 15f
                textView.layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                )
                ingredientsContainer.addView(textView)
            }
        }
        showParent()
        showProgressBar(false)
    }

    private fun displayErrorScreen(errorMessage: String) {
        recipeTitle.text = "Error retrieveing recipe..."
        recipeSocialScore.text = ""
        val textView = TextView(this)
        if (errorMessage != "") {
            textView.text = errorMessage
        } else {
            textView.text = "Error"
        }
        textView.textSize = 15f
        textView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        ingredientsContainer.addView(textView)
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
        Glide.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(R.drawable.ic_launcher_background)
            .into(recipeImage)
        showParent()
        showProgressBar(false)
    }

    private fun getIncomingIntent() {
        if (intent.hasExtra("RECIPE")) {
            val recipe: Recipe = intent.getParcelableExtra("RECIPE")
            recipe.recipe_id?.let { viewModel.searchForRecipeIngredients(it) }
            Log.d(TAG, "debug -> ${recipe.recipe_id}")
        }
    }


    private fun showParent() {
        scrollView.visibility = View.VISIBLE
    }
}
