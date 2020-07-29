package com.ramadan.app.di.module

import androidx.lifecycle.ViewModel
import com.ramadan.app.di.vmfactory.ViewModelKey
import com.ramadan.app.ui.features.recipes.viewmodel.RecipesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RecipesVmModule {
    @Binds
    @IntoMap
    @ViewModelKey(RecipesViewModel::class)
    internal abstract fun bindRecipesViewModel(viewModel: RecipesViewModel): ViewModel
}