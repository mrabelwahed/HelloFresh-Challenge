package com.ramadan.app.ui.features.recipes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.app.R
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.app.ui.features.recipes.view.RecipesAdapter.RecipesViewHolder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter :ListAdapter <Recipe , RecipesViewHolder>(RecipeDiffCallback()){

    private lateinit var listener: OnClickListener
    lateinit var  context : Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = getItem(position)
         holder.bindTo(recipe)
         holder.itemView.setOnClickListener { listener.onClick(recipe) }
    }

    fun setClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    inner class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindTo(recipe: Recipe){
            itemView.nameView.text = recipe.name
            Picasso.get().load(recipe.image).into(itemView.recipeView);
            if (recipe.favorite)
                itemView.favoriteView.setImageResource(R.drawable.ic_baseline_favorite_24)
            else
               itemView.favoriteView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }
}

class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem == newItem
    }

}