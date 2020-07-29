package com.ramadan.domain.interactor

import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Flowable

class GetRecipesList (private val repository: RecipesRepository): Usecase<Unit,List<Recipe>> {
    override fun execute(param: Unit): Flowable<List<Recipe>> {
        return repository.getRecipes()
    }
}