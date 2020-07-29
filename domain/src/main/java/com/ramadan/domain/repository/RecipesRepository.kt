package com.ramadan.domain.repository

import com.ramadan.domain.model.Recipe
import io.reactivex.Flowable

interface RecipesRepository {
    fun getRecipes(): Flowable<List<Recipe>>
}