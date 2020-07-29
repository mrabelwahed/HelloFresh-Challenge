package com.ramadan.app.di.component

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.module.AppModule
import com.ramadan.app.di.scope.AppScope
import dagger.Component
@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(target: RecipeApp)
}