package com.ramadan.app.di.module

import android.content.Context
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.scope.AppScope
import com.ramadan.data.AppDatabase
import com.ramadan.data.repository.RecipesRepositoryImpl
import com.ramadan.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides


@Module
open class AppModule {

    @AppScope
    @Provides
    fun provideContext(app:RecipeApp): Context = app.applicationContext

//    @AppScope
//    @Provides
//    fun provideRecipesRepository() : RecipesRepository = RecipesRepositoryImpl(app,appDatabase)

//    @AppScope
//    @Provides
//    fun provideRecipesRepository(app: RecipeApp , appDatabase: AppDatabase)  = RecipesRepositoryImpl(app,appDatabase)

//    @AppScope
//    @Provides
//    fun provideAppDataBase(): AppDatabase {
//        return AppDatabase.createAppDatabase(app)
//    }

//    @AppScope
//    @Provides
//    fun provideRecipeDAO(appDatabase: AppDatabase) = appDatabase.recipeDAO()

//    @AppScope
//    @Provides
//    fun provideRecipesRepository(appDatabase: AppDatabase) = RecipesRepositoryImpl(app,appDatabase)

}