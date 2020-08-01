package com.ramadan.domain.repository

import com.ramadan.domain.model.Recipe
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface RecipesRepository {
    fun getRecipes(): Flowable<List<Recipe>>
    fun saveRecipes(recipes: List<Recipe>)
    fun updateRecipe(recipe:Recipe): Completable
    fun getRecipeById(id: String): Single<Recipe>

}