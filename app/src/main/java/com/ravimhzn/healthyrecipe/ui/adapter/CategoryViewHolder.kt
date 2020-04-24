package com.ravimhzn.healthyrecipe.ui.adapter

import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ravimhzn.healthyrecipe.extension.setImageUri
import com.ravimhzn.healthyrecipe.model.Recipe
import kotlinx.android.synthetic.main.layout_category_list_item.view.*

class CategoryViewHolder(
    itemView: View,
    private val mOnRecipeListener: OnRecipeListener
) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val categoryTitle = itemView.category_title
    val categoryImage = itemView.category_image


    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        categoryTitle.text.toString().let {
            mOnRecipeListener.onCategoryClick(it)
        }
    }

    fun bind(recipe: Recipe) {
        val path = Uri.parse(
            "android.resource://com.ravimhzn.healthyrecipe/drawable/" + recipe.image_url //TODO("NOT A GOOD IMPLEMENTATION")
        )
        categoryImage.setImageUri(id = path)
        categoryTitle.text = recipe.title
    }
}
