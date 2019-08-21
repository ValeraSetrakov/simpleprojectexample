package com.valerasetrakov.simpleexample.data.events

import com.valerasetrakov.simpleexample.data.events.entity.Event
import com.valerasetrakov.simpleexample.data.events.entity.EventType
import com.valerasetrakov.simpleexample.data.events.entity.ResponseEvent

object EventMapper {

    fun mapToEvent(responseEvent: ResponseEvent) = Event(
        id = responseEvent.id,
        title = responseEvent.title,
        placeTitle = responseEvent.placeTitle,
        type = when(responseEvent.type) {
            0 -> EventType.FirstType
            1 -> EventType.SecondType
            2 -> EventType.ThirdType
            else -> throw RuntimeException("This type is not supported")
        },
        description = responseEvent.description,
        image = responseEvent.image
    )

    fun mapToEvents(responseEvents: List<ResponseEvent>) = responseEvents.map { mapToEvent(it) }
}