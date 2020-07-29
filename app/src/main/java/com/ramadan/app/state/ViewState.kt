package com.ramadan.app.state

import com.ramadan.app.error.Failure

sealed class ViewState {
    object Loading : ViewState()
    data class Error(val failure: Failure?) : ViewState()
    data class Success<T>(val item: T) : ViewState()
}