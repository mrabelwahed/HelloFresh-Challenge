package com.ramadan.data.repository

import RecipeMapper
import android.app.Application
import com.ramadan.data.AppDatabase
import com.ramadan.data.RecipeEntity
import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class RecipesRepositoryImpl ( private val app: Application , private val appDatabase: AppDatabase):RecipesRepository{

    override fun getRecipes(): Flowable<List<Recipe>> {
        return  Flowable.concat(
            getLocalRecipes(),
            getFileRecipes().doOnNext { data -> saveRecipes(data) }
        )
    }

    private fun getFileRecipes():Flowable<List<Recipe>>{
        val inputStream = app.assets.open("recipes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val items = parseJsonString(buffer.toString(Charset.defaultCharset()))
        return Flowable.just(RecipeMapper.transform(items))
    }

    private fun getLocalRecipes():Flowable<List<Recipe>>{
        return Flowable.fromCallable {
            RecipeMapper.transformLocalRecipes(appDatabase.recipeDAO().getAllRecipes())
        }
    }

    override fun saveRecipes(recipes: List<Recipe>) {
        recipes.forEach {
            val localRecipe = appDatabase.recipeDAO().getRecipeById(it.id)
            if (localRecipe!=null)
               it.favorite= localRecipe.favorite
            else
                it.favorite = false
        }
       appDatabase.recipeDAO().saveRecipes(RecipeMapper.transformToEntityCollection(recipes))
    }

    override fun updateRecipe(recipe: Recipe): Completable {
        return Completable.fromCallable {
            appDatabase.recipeDAO().updateRecipe(RecipeMapper.transformToEntity(recipe))
        }
    }

    override fun getRecipeById(id: String): Single<Recipe> {
        return  Single.fromCallable {
            RecipeMapper.transformLocalRecipe( appDatabase.recipeDAO().getRecipeById(id))
         }
    }

    private fun parseJsonString(jsonString: String): List<com.ramadan.data.model.Recipe> {
        val recipesArray = JSONArray(jsonString)
        val recipesItems = mutableListOf<com.ramadan.data.model.Recipe>()
        recipesArray.forEach { recipeObject ->
            val id = recipeObject.optString("id")
            val name = recipeObject.optString("name")
            val description = recipeObject.optString("description")
            val image = recipeObject.optString("image")
            recipesItems.add(com.ramadan.data.model.Recipe(id,name,description,image,false))
        }
        return recipesItems
    }

    private fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
        for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
    }

}