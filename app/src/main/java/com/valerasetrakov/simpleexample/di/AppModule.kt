package com.valerasetrakov.simpleexample.di

import android.app.Application
import android.content.Context
import com.valerasetrakov.simpleexample.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class, DataModule::class])
class AppModule(val application: App) {
    @Provides @Singleton fun provideApplication(): Application = application
    @Provides @Singleton fun provideApp(): App = application
    @Provides @Singleton fun provideContext(): Context = application
}
