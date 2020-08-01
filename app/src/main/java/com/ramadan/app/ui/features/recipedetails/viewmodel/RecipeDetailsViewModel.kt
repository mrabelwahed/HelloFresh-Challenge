package com.ramadan.app.ui.features.recipedetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadan.app.error.Failure
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.recipes.mapper.RecipeMapper
import com.ramadan.app.ui.features.recipes.model.Recipe
import com.ramadan.domain.interactor.GetRecipeById
import com.ramadan.domain.interactor.UpdateRecipe
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeById: GetRecipeById,
    private val updateRecipeById: UpdateRecipe): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val uiState = MutableLiveData<ViewState>()
    val liveUIState: LiveData<ViewState>
        get() = uiState

    fun getRecipeById(id : String) {
        compositeDisposable.add(getRecipeById.execute(id).subscribe(
            { res -> uiState.value = ViewState.Success(res) },
            { error -> uiState.value = setFailure(error) }
        ))
    }

    fun updateRecipe(recipe:Recipe) {
        recipe.favorite = !recipe.favorite
        compositeDisposable.add(updateRecipeById.execute(RecipeMapper.transformToDomainRecipe(recipe)).subscribe {
            val recipe = RecipeMapper.transformToDomainRecipe(recipe)
             uiState.value = ViewState.Success(recipe)
        })
    }

    private fun setFailure(throwable: Throwable): ViewState {
        return when (throwable) {
            is UnknownHostException -> ViewState.Error(Failure.NetworkConnection)
            else -> ViewState.Error(Failure.UnExpectedError)
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}