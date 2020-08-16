package com.ramadan.domain.interactor


interface Usecase<P,R> {
    fun execute(param : P):R
}