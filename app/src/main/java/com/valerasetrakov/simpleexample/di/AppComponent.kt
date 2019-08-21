package com.valerasetrakov.simpleexample.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getViewModelFactory(): ViewModelFactory
}