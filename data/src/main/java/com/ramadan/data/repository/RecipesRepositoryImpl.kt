package com.ramadan.data.repository

import RecipeMapper
import android.app.Application
import android.content.Context
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
import javax.inject.Inject

private const val ID = "id"
private const val NAME ="name"
private const val DESC = "description"
private const val IMAGE ="image"
private const val RECIPES_FILE = "recipes.json"


class RecipesRepositoryImpl (val context: Context , val appDatabase: AppDatabase):RecipesRepository{

    override fun getRecipes(): Flowable<List<Recipe>> {
        return  Flowable.concat(
            getLocalRecipes(),
            getFileRecipes().doOnNext { data -> saveRecipes(data) }
        )
    }

    private fun getFileRecipes():Flowable<List<Recipe>>{
        val inputStream = context.assets.open(RECIPES_FILE)
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
            val id = recipeObject.optString(ID)
            val name = recipeObject.optString(NAME)
            val description = recipeObject.optString(DESC)
            val image = recipeObject.optString(IMAGE)
            recipesItems.add(com.ramadan.data.model.Recipe(id,name,description,image,false))
        }
        return recipesItems
    }

    private fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
        for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
    }

}