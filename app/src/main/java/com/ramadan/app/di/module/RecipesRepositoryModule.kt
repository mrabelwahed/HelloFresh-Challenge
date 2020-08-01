package com.ramadan.app.di.module

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.scope.AppScope
import com.ramadan.data.AppDatabase
import com.ramadan.data.repository.RecipesRepositoryImpl
import com.ramadan.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides

@Module
class RecipesRepositoryModule(val app: RecipeApp , val appDatabase: AppDatabase){
    @Provides
    fun provideRecipesRepository() :RecipesRepository = RecipesRepositoryImpl(app,appDatabase)
}