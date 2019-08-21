package com.valerasetrakov.simpleexample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.valerasetrakov.simpleexample.data.events.EventDao
import com.valerasetrakov.simpleexample.data.events.entity.Event
import com.valerasetrakov.simpleexample.data.events.entity.EventTypeConverter
import com.valerasetrakov.simpleexample.data.profile.ProfileDao
import com.valerasetrakov.simpleexample.data.profile.entity.Profile

@Database(entities = [Profile::class, Event::class], version = 1)
@TypeConverters(value = [EventTypeConverter::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun eventDao(): EventDao
}