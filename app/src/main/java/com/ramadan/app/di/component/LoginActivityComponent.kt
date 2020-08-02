package com.ramadan.app.di.component

import com.ramadan.app.di.module.LoginVmModule
import com.ramadan.app.di.module.RecipeDetailsVmModule
import com.ramadan.app.di.module.UserRepositoryModule
import com.ramadan.app.di.module.ViewModelFactoryModule
import com.ramadan.app.di.scope.ActivityScope
import com.ramadan.app.ui.features.login.view.LoginActivity
import dagger.Component

@ActivityScope
@Component(
    modules = [
        UserRepositoryModule::class,
        LoginVmModule::class,
        ViewModelFactoryModule::class
    ],
    dependencies = [AppComponent::class]
)
interface LoginActivityComponent {
    fun inject(target: LoginActivity)
}