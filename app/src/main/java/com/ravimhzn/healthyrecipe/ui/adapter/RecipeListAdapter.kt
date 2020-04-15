package com.ravimhzn.healthyrecipe.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.healthyrecipe.R
import com.ravimhzn.healthyrecipe.extension.setImageUrl
import com.ravimhzn.healthyrecipe.model.Recipe
import kotlinx.android.synthetic.main.layout_recipe_list_item.view.*
import java.lang.String
import kotlin.math.roundToLong

class RecipeListAdapter() :
    RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder>() {
    private lateinit var arrRecipeList: List<Recipe>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeListViewHolder {
        return RecipeListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_recipe_list_item, parent, false)

        )
    }

    override fun getItemCount(): Int {
        return if (::arrRecipeList.isInitialized) arrRecipeList.size else 0
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder, position: Int) {
        holder.bind(arrRecipeList[position])
    }

    fun setRecipe(recipeList: List<Recipe>) {
        this.arrRecipeList = recipeList
        notifyDataSetChanged()
    }


    class RecipeListViewHolder(
        itemView: View
    ) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val title = itemView.recipe_title
        val publisher = itemView.recipe_publisher
        val socialScore = itemView.recipe_social_score
        val image = itemView.recipe_image

        init {
            // itemView.setOnClickListener(this)
        }

        fun bind(recipe: Recipe) {
            title.text = recipe.title
            publisher.text = recipe.publisher
            socialScore.text = String.valueOf(recipe.social_rank?.let { it.roundToLong() })
            image.setImageUrl(url = recipe.image_url)
        }

        override fun onClick(p0: View?) {
            //   onRecipeListener.onRecipeClick(adapterPosition)
        }
    }
}