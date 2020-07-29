package com.ramadan.app.di.module

import androidx.recyclerview.widget.LinearLayoutManager
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.scope.ActivityScope
import com.ramadan.app.ui.features.recipes.view.RecipesAdapter
import dagger.Module
import dagger.Provides

@Module
class RecipesActivityModule(private val app: RecipeApp) {
    @ActivityScope
    @Provides
    fun provideRecipesAdapter(): RecipesAdapter = RecipesAdapter()

    @ActivityScope
    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(app)
}