package com.ramadan.app.ui.features.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ramadan.app.error.Failure
import com.ramadan.app.state.SingleLiveEvent
import com.ramadan.app.state.ViewState
import com.ramadan.domain.interactor.LoginUser
import io.reactivex.disposables.CompositeDisposable
import java.net.UnknownHostException
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userLogin: LoginUser): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val uiState = SingleLiveEvent<ViewState>()
    val liveUIState: LiveData<ViewState>
        get() = uiState

    fun login(email:String, password:String){
        uiState.call()
        userLogin.setLoginData(email,password)
        compositeDisposable.add(userLogin.execute(Unit).subscribe(
            {res -> uiState.value = ViewState.Success(res)},
            {error -> uiState.value = setFailure(error)}
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