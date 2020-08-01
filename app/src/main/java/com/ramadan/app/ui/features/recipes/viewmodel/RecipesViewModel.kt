package com.ramadan.app.ui.features.recipes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadan.app.error.Failure
import com.ramadan.app.state.ViewState
import com.ramadan.app.ui.features.recipes.mapper.RecipeMapper
import com.ramadan.domain.interactor.GetRecipesList
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class RecipesViewModel @Inject constructor(private val usecase: GetRecipesList) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val uiState = MutableLiveData<ViewState>()
    val liveUIState: LiveData<ViewState>
        get() = uiState

    fun getRecipesList() {
        compositeDisposable.add(usecase.execute(Unit).subscribe(
            { res -> uiState.value = ViewState.Success(RecipeMapper.transform(res)) },
            { error -> uiState.value = setFailure(error) }
        ))
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