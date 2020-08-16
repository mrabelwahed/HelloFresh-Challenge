package com.ramadan.app.di.module

import com.ramadan.data.repository.UserRepositoryImpl
import com.ramadan.domain.repository.UserRepository
import dagger.Module
import dagger.Provides

@Module
class UserRepositoryModule() {
    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()
}