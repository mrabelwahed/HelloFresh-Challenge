package com.ramadan.app.error

sealed class Failure {
    object NetworkConnection : Failure()
    object UnExpectedError : Failure()
}