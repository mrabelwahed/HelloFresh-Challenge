package com.ramadan.app.di.module

import androidx.lifecycle.ViewModel
import com.ramadan.app.di.vmfactory.ViewModelKey
import com.ramadan.app.ui.features.login.viewmodel.LoginViewModel
import com.ramadan.app.ui.features.recipedetails.viewmodel.RecipeDetailsViewModel
import com.ramadan.app.ui.features.recipes.viewmodel.RecipesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginVmModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}