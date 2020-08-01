package com.ramadan.app.ui.features.recipedetails.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.ramadan.app.R
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.component.DaggerAppComponent
import com.ramadan.app.di.component.DaggerRecipeDetailsActivityComponent

import com.ramadan.app.di.module.RecipesRepositoryModule
import com.ramadan.app.di.vmfactory.ViewModelFactory
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.recipedetails.viewmodel.RecipeDetailsViewModel
import com.ramadan.app.ui.features.recipes.mapper.RecipeMapper
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.data.AppDatabase
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.recipe_item.nameView
import kotlinx.android.synthetic.main.recipe_item.recipeView
import javax.inject.Inject

class RecipeDetailsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var recipeDetailsViewModel: RecipeDetailsViewModel
    lateinit var recipeId:String
    lateinit var recipe:Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        initDI()
        recipeDetailsViewModel = ViewModelProvider(this, viewModelFactory)[RecipeDetailsViewModel::class.java]
        recipeId = intent.getStringExtra("recipe_id")

        observeRecipe()

        favoriteView.setOnClickListener {
            recipeDetailsViewModel.updateRecipe(recipe)
        }
    }

    private fun initDI() {
        val database = AppDatabase.createAppDatabase(applicationContext)
        val appComponent = DaggerAppComponent.create()
        val recipeDetailsActivityComponent = DaggerRecipeDetailsActivityComponent.builder()
            .appComponent(appComponent)
            .recipesRepositoryModule(RecipesRepositoryModule(application as RecipeApp,database))
            .build()
        recipeDetailsActivityComponent.inject(this)
    }

    private fun setData(recipe: Recipe) {
        nameView.text = recipe.name
        descriptionView.text = recipe.description
        Glide.with(this).load(recipe.image).into(recipeView)
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
                is ViewState.Loading -> {
                    handleUILoading()
                }
            }
        }
    }

    private fun handleUILoading() {

    }

    private fun handleUIError() {
        Toast.makeText(this,"something went wrong",Toast.LENGTH_SHORT).show()
    }

    private fun handleUISuccess(recipe: Recipe) {
        setData(recipe)
        if(recipe.favorite)
            favoriteView.setImageResource(R.drawable.ic_baseline_favorite_24)
        else
            favoriteView.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }
}