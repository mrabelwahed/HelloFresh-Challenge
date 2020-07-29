package com.ramadan.app.di.module

import androidx.lifecycle.ViewModelProvider
import com.ramadan.app.di.vmfactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}