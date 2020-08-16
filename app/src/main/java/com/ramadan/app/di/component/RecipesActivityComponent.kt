package com.ramadan.app.di.component

import com.ramadan.app.di.module.*
import com.ramadan.app.di.scope.ActivityScope
import com.ramadan.app.ui.features.recipes.view.RecipesActivity
import dagger.Component

@ActivityScope
@Component(
    modules = [
        RecipesActivityModule::class ,
        RecipesVmModule::class ,
        ViewModelFactoryModule::class
    ],
    dependencies = [AppComponent::class]
)
interface RecipesActivityComponent {
    fun inject(target: RecipesActivity)
}