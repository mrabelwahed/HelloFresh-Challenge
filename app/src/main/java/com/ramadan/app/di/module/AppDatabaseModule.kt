package com.ramadan.app.di.module

import android.content.Context
import androidx.room.Room
import com.ramadan.data.AppDatabase
import com.ramadan.data.repository.RecipesRepositoryImpl
import com.ramadan.domain.repository.RecipesRepository
import dagger.Module
import dagger.Provides

@Module
class AppDatabaseModule {

    @Provides
    fun provideRecipesRepository(context: Context, appDatabase: AppDatabase): RecipesRepository =
        RecipesRepositoryImpl(context,appDatabase)

    @Provides
    fun provideAppDataBase(context: Context): AppDatabase {
        return  Room.databaseBuilder(context, AppDatabase::class.java, "hellofresh-db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRecipeDAO(appDatabase: AppDatabase) = appDatabase.recipeDAO()
}