package com.ravimhzn.healthyrecipe.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.healthyrecipe.extension.setImageUrl
import com.ravimhzn.healthyrecipe.model.Recipe
import kotlinx.android.synthetic.main.layout_recipe_list_item.view.*
import java.lang.String
import kotlin.math.roundToLong


class RecipeListViewHolder(
    itemView: View,
    private val mOnRecipeListener: OnRecipeListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val title = itemView.recipe_title
    val publisher = itemView.recipe_publisher
    val socialScore = itemView.recipe_social_score
    val image = itemView.recipe_image

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(recipe: Recipe) {
        title.text = recipe.title
        publisher.text = recipe.publisher
        socialScore.text = String.valueOf(recipe.social_rank?.let { it.roundToLong() })
        image.setImageUrl(url = recipe.image_url)
    }

    override fun onClick(p0: View?) {
        mOnRecipeListener.onRecipeClick(adapterPosition)
    }
}