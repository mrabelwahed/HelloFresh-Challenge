package com.ramadan.data.di

import android.content.Context
import com.ramadan.data.AppDatabase
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return AppDatabase.createAppDatabase(context)
    }


    @Provides
    fun provideRecipeDAO(appDatabase: AppDatabase) = appDatabase.recipeDAO()
}