package com.ramadan.app.di.component

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.module.AppDatabaseModule
import com.ramadan.app.di.module.AppModule
import com.ramadan.app.di.scope.AppScope
import com.ramadan.domain.repository.RecipesRepository
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
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RecipeApp): Builder
        fun build(): AppComponent
    }
    fun getRecipesRepository():RecipesRepository
}