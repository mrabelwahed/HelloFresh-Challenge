package com.ramadan.data.repository

import android.app.Application
import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Flowable
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset

class RecipesRepositoryImpl (private val app: Application):RecipesRepository{
    override fun getRecipes(): Flowable<List<Recipe>> {
        val inputStream = app.assets.open("recipes.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val items = parseJsonString(buffer.toString(Charset.defaultCharset()))
        return Flowable.just(RecipeMapper.transform(items))
    }

    private fun parseJsonString(jsonString: String): List<com.ramadan.data.model.Recipe> {
        val recipesArray = JSONArray(jsonString)
        val recipesItems = mutableListOf<com.ramadan.data.model.Recipe>()
        recipesArray.forEach { recipeObject ->
            val id = recipeObject.optString("id")
            val name = recipeObject.optString("name")
            val description = recipeObject.optString("description")
            val image = recipeObject.optString("image")
            recipesItems.add(com.ramadan.data.model.Recipe(id,name,description,image))
        }
        return recipesItems
    }

    private fun JSONArray.forEach(jsonObject: (JSONObject) -> Unit) {
        for (index in 0 until this.length()) jsonObject(this[index] as JSONObject)
    }

}