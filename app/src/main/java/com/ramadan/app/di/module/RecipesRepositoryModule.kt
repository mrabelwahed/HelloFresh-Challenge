package com.ramadan.app.di.module

import com.ramadan.app.RecipeApp
import com.ramadan.data.repository.RecipesRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RecipesRepositoryModule(private val app: RecipeApp) {
    @Provides
    fun provideRecipesRepository() = RecipesRepositoryImpl(app)
}