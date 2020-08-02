package com.ramadan.app.di.module

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.scope.AppScope
import com.ramadan.data.AppDatabase
import com.ramadan.data.repository.RecipesRepositoryImpl
import com.ramadan.data.repository.UserRepositoryImpl
import com.ramadan.domain.repository.RecipesRepository
import com.ramadan.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UserRepositoryModule(){
    @Provides
    fun provideUserRepository() :UserRepository = UserRepositoryImpl()
}