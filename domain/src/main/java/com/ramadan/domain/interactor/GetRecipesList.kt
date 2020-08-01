package com.ramadan.domain.interactor

import com.ramadan.domain.model.Recipe
import com.ramadan.domain.repository.RecipesRepository
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetRecipesList @Inject constructor (private val repository: RecipesRepository): Usecase<Unit,Flowable<List<Recipe>>> {
    override fun execute(param: Unit): Flowable<List<Recipe>> {
        return repository.getRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}