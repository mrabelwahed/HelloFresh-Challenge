package com.ramadan.domain.interactor

import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetRecipeById @Inject constructor (private val repository: RecipesRepository) :Usecase<String,Single<Recipe>> {
    override fun execute(id: String): Single<Recipe> {
        return  repository.getRecipeById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}