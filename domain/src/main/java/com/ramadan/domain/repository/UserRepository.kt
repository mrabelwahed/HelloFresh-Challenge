package com.ramadan.domain.repository

import io.reactivex.Single

interface UserRepository {
    fun login(email: String , password:String): Single<Boolean>
}