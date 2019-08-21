package com.valerasetrakov.simpleexample.screen.main.events.list

import com.valerasetrakov.simpleexample.data.events.EventDao
import com.valerasetrakov.simpleexample.data.events.EventRepository
import javax.inject.Inject

class EventsFragmentRepository @Inject constructor(private val eventRepository: EventRepository): EventDao by eventRepository {
    suspend fun requestEvents() = eventRepository._requestEvents()
}