package com.ramadan.domain.interactor

import com.ramadan.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginUser @Inject constructor(
    val repository: UserRepository
) : Usecase<Unit, Single<Boolean>> {
    lateinit var email:String
    lateinit var password:String

    override fun execute(param: Unit): Single<Boolean> {
        return repository.login(email, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun setLoginData(email:String, password:String){
        this.email = email
        this.password = password
    }
}