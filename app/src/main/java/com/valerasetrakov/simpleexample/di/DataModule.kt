package com.valerasetrakov.simpleexample.di

import android.content.Context
import androidx.room.Room
import com.valerasetrakov.simpleexample.data.api.ContentApi
import com.valerasetrakov.simpleexample.data.api.MockContentApi
import com.valerasetrakov.simpleexample.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, "App database")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideContentApi(): ContentApi = MockContentApi()

    @Singleton
    @Provides
    fun provideProfileDao(appDatabase: AppDatabase) = appDatabase.profileDao()

    @Singleton
    @Provides
    fun provideEventDao(appDatabase: AppDatabase) = appDatabase.eventDao()
}