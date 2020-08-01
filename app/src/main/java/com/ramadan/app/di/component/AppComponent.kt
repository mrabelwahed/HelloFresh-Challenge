package com.ramadan.app.di.component

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.module.AppModule
import com.ramadan.app.di.scope.AppScope
import com.ramadan.data.AppDatabase
import com.ramadan.data.di.AppDatabaseModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
        AppDatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(target: RecipeApp)
}