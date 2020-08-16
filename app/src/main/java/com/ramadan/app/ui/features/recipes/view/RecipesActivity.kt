package com.ramadan.app.ui.features.recipes.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramadan.app.R
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.component.DaggerRecipesActivityComponent
import com.ramadan.app.di.module.RecipesActivityModule
import com.ramadan.app.di.vmfactory.ViewModelFactory
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.recipedetails.view.RecipeDetailsActivity
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.app.ui.features.recipes.viewmodel.RecipesViewModel
import kotlinx.android.synthetic.main.activity_recipes.*
import javax.inject.Inject

class RecipesActivity : AppCompatActivity(), OnClickListener {

    @Inject
    lateinit var recipesAdapter: RecipesAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        initDI()
        recipesViewModel = ViewModelProvider(this, viewModelFactory)[RecipesViewModel::class.java]
        recipesViewModel.uiState.value = ViewState.Loading
        setupRecipesRecyclerview()
        observeRecipesList()
    }

    private fun initDI() {
        val appComponent = (application as RecipeApp).getApplicationComponent()
        val recipesActivityComponent = DaggerRecipesActivityComponent.builder()
            .appComponent(appComponent)
            .recipesActivityModule(RecipesActivityModule(application as RecipeApp))
            .build()
        recipesActivityComponent.inject(this)
    }

    private fun observeRecipesList() {
        recipesViewModel.liveUIState.observeForever {
            when (it) {
                is ViewState.Success<*> -> {
                    val recipesList = (it.item as ArrayList<Recipe>)
                    handleUISuccess()
                    recipesAdapter.submitList(recipesList)
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

    private fun handleUISuccess() {
        progressBar.visibility = View.GONE
        recipesRecyclerView.visibility = View.VISIBLE
        errorText.visibility = View.GONE
    }

    private fun handleUIError() {
        progressBar.visibility = View.GONE
        recipesRecyclerView.visibility = View.GONE
        errorText.visibility = View.VISIBLE
    }

    private fun handleUILoading() {
        progressBar.visibility = View.VISIBLE
        recipesRecyclerView.visibility = View.GONE
        errorText.visibility = View.GONE
    }

    private fun setupRecipesRecyclerview() {
        with(recipesRecyclerView) {
            adapter = recipesAdapter
            layoutManager = linearLayoutManager
        }
        recipesAdapter.setClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        recipesViewModel.getRecipesList()
    }

    override fun onClick(recipe: Recipe) {
        Intent(this, RecipeDetailsActivity::class.java).apply {
            putExtra(RECIPE_ID, recipe.id)
            startActivity(this)
        }
    }

    companion object {
        const val RECIPE_ID = "recipe_ID"
    }

}