package com.ramadan.app

import android.app.Application
import com.ramadan.app.di.component.AppComponent
import com.ramadan.app.di.component.DaggerAppComponent


open class RecipeApp : Application() {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = getApplicationComponent()
    }

    open fun getApplicationComponent(): AppComponent {

        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}