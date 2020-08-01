package com.ramadan.app

import android.app.Application
import com.ramadan.app.di.component.AppComponent
import com.ramadan.app.di.component.DaggerAppComponent
import com.ramadan.app.di.module.AppModule

class RecipeApp  : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = getApplicationComponent()
        appComponent.inject(this)
    }

    open fun getApplicationComponent(): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule())
            .build()
}