package com.ramadan.app.ui.features.recipedetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ramadan.app.R
import com.ramadan.app.ui.features.recipes.model.Recipe
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.recipe_item.nameView
import kotlinx.android.synthetic.main.recipe_item.recipeView

class RecipeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        val recipe = intent.getParcelableExtra<Recipe>("recipe")
        setData(recipe)
    }

    private fun setData(recipe: Recipe) {
        nameView.text = recipe.name
        descriptionView.text = recipe.description
        Glide.with(this).load(recipe.image).into(recipeView)
    }
}