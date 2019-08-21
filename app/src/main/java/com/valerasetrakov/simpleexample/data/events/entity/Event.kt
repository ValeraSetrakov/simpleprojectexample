package com.valerasetrakov.simpleexample.data.events.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
data class Event(
    @PrimaryKey
    val id: String,
    val title: String,
    val placeTitle: String,
    val type: EventType,
    val description: String,
    val image: String
)

enum class EventType {
    FirstType, SecondType, ThirdType
}

class EventTypeConverter {

    @TypeConverter
    fun toEventType(value: Int): EventType {
        return EventType.values()[value]
    }

    @TypeConverter
    fun fromEventType(eventType: EventType): Int {
        return eventType.ordinal
    }
}