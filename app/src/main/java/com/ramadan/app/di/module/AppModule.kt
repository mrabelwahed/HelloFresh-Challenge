package com.ramadan.app.di.module

import android.content.Context
import com.ramadan.app.RecipeApp
import com.ramadan.app.di.scope.AppScope
import dagger.Module

@Module
open class AppModule(private val app: RecipeApp) {
    @AppScope
    fun provideContext(): Context = app
}