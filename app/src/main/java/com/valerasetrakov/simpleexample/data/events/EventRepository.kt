package com.valerasetrakov.simpleexample.data.events

import com.valerasetrakov.simpleexample.data.api.ContentApi
import com.valerasetrakov.simpleexample.data.events.entity.RequestEvent
import javax.inject.Inject

class EventRepository @Inject constructor(private val eventDao: EventDao,
                                          private val contentApi: ContentApi): EventDao by eventDao, ContentApi by contentApi {
    suspend fun requestEvent(id: String) {
        val responseEvent = requestEvent(RequestEvent(id))
        val event = EventMapper.mapToEvent(responseEvent)
        eventDao.insert(event)
    }

    suspend fun _requestEvents() {
        val responseEvents = requestEvents()
        val events = EventMapper.mapToEvents(responseEvents)
        eventDao.insert(events)
    }


}