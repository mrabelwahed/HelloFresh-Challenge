package com.ramadan.app.ui.features.recipedetails.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ramadan.app.R
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.component.DaggerRecipeDetailsActivityComponent
import com.ramadan.app.di.vmfactory.ViewModelFactory
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.recipedetails.viewmodel.RecipeDetailsViewModel
import com.ramadan.app.ui.features.recipes.mapper.RecipeMapper
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.app.ui.features.recipes.view.RecipesActivity.Companion.RECIPE_ID
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.recipe_item.nameView
import kotlinx.android.synthetic.main.recipe_item.recipeView
import javax.inject.Inject

class RecipeDetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel
    lateinit var recipeId: String
    lateinit var recipe: Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initDI()
        recipeDetailsViewModel =
            ViewModelProvider(this, viewModelFactory)[RecipeDetailsViewModel::class.java]
        recipeId = intent.getStringExtra(RECIPE_ID)

        observeRecipe()

        favoriteView.setOnClickListener {
            recipeDetailsViewModel.updateRecipe(recipe)
        }
    }

    private fun initDI() {
        // val database = AppDatabase.createAppDatabase(applicationContext)
        val recipeDetailsActivityComponent = DaggerRecipeDetailsActivityComponent.builder()
            .appComponent((application as RecipeApp).getApplicationComponent())
            //.recipesRepositoryModule(RecipesRepositoryModule())
            .build()
        recipeDetailsActivityComponent.inject(this)
    }

    private fun setData(recipe: Recipe) {
        nameView.text = recipe.name
        descriptionView.text = recipe.description
        Picasso.get().load(recipe.image).into(recipeView)
    }

    override fun onResume() {
        super.onResume()
        recipeDetailsViewModel.getRecipeById(recipeId)
    }

    private fun observeRecipe() {
        recipeDetailsViewModel.liveUIState.observeForever {
            when (it) {
                is ViewState.Success<*> -> {
                    val recipe = RecipeMapper.transform(it.item as com.ramadan.domain.model.Recipe)
                    this.recipe = recipe
                    handleUISuccess(recipe)
                }
                is ViewState.Error -> {
                    handleUIError()
                }
            }
        }
    }


    private fun handleUIError() {
        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
    }

    private fun handleUISuccess(recipe: Recipe) {
        setData(recipe)
        if (recipe.favorite)
            favoriteView.setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            favoriteView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }
}