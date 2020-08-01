package com.ramadan.app.di.component

import com.ramadan.app.RecipeApp
import com.ramadan.app.di.module.*
import com.ramadan.app.di.scope.ActivityScope
import com.ramadan.app.ui.features.recipedetails.view.RecipeDetailsActivity
import com.ramadan.app.ui.features.recipes.view.RecipesActivity
import com.ramadan.data.AppDatabase
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(
    modules = [
        RecipesRepositoryModule::class,
        RecipeDetailsVmModule::class ,
        ViewModelFactoryModule::class
    ],
    dependencies = [AppComponent::class]
)
interface RecipeDetailsActivityComponent {
    fun inject(target: RecipeDetailsActivity)
}