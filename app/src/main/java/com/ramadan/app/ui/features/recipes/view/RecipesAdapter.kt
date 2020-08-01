package com.ramadan.app.ui.features.recipes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ramadan.app.R
import com.ramadan.app.ui.features.recipes.model.Recipe
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {

    val recipeItems = ArrayList<Recipe>()
    private lateinit var listener: OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        return RecipesViewHolder(view)
    }

    override fun getItemCount() = recipeItems.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe = recipeItems[position]
        holder.itemView.nameView.text = recipe.name
        Glide.with(holder.itemView.context).load(recipe.image).into(holder.itemView.recipeView)
        if (recipe.favorite)
            holder.itemView.favoriteView.setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            holder.itemView.favoriteView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        holder.itemView.setOnClickListener { listener.onClick(position, it) }
    }

    fun setRecipesList(newListOfRecipesItems: List<Recipe>) {
        recipeItems.clear()
        recipeItems.addAll(newListOfRecipesItems)
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    class RecipesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


}