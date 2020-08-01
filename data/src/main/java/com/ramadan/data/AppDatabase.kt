package com.ramadan.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract  fun recipeDAO(): RecipeDAO

    companion object{
        fun createAppDatabase(context:Context): AppDatabase {
            return  Room.databaseBuilder(context, AppDatabase::class.java, "hellofresh-db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

}