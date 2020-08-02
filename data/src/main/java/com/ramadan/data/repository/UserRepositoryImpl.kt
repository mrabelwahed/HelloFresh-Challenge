package com.ramadan.data.repository

import com.ramadan.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject

const val EMAIL = "admin@hellofresh.com"
const val PASSWORD = "123456"

class UserRepositoryImpl  : UserRepository {
    override fun login(email: String, password: String): Single<Boolean> {
        if (email == EMAIL && password == PASSWORD)
            return Single.just(true)
        return Single.just(false)
    }
}