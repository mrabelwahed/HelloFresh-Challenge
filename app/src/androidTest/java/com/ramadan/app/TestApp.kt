package com.ramadan.app

import TestAppModule
import com.ramadan.app.di.component.AppComponent
import com.ramadan.app.di.component.DaggerAppComponent

class TestApp : RecipeApp() {
    override fun getApplicationComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(TestAppModule())
            .build()
    }
}