package com.ramadan.domain.interactor

import io.reactivex.Flowable

interface Usecase<P,R> {
    fun execute(param : P):R
}