package com.ramadan.domain.interactor

import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpdateRecipe @Inject constructor (private val repository: RecipesRepository) : Usecase<Recipe, Completable> {
    override fun execute(recipe: Recipe): Completable {
        return repository.updateRecipe(recipe)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}