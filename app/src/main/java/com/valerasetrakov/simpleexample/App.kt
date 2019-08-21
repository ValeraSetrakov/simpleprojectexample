package com.valerasetrakov.simpleexample

import android.app.Application
import com.valerasetrakov.simpleexample.di.AppComponent
import com.valerasetrakov.simpleexample.di.AppModule
import com.valerasetrakov.simpleexample.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}