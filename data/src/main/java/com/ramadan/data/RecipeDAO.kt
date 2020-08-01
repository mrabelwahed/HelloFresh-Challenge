package com.ramadan.data

import androidx.room.*
import com.ramadan.domain.model.Recipe

@Dao
interface RecipeDAO{
     @Query("SELECT * FROM recipe")
     fun getAllRecipes():List<RecipeEntity>

     @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun saveRecipes(recipes:List<RecipeEntity>)

     @Query("SELECT * FROM recipe WHERE id=:id")
     fun getRecipeById(id:String):RecipeEntity

     @Update
     fun updateRecipe(recipe:RecipeEntity)
}