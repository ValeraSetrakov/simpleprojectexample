package com.valerasetrakov.simpleexample.screen.main.events.list

import android.app.Application
import androidx.lifecycle.Transformations
import com.valerasetrakov.base.invokeAsyncOperation
import com.valerasetrakov.simpleexample.adapters.event.EventViewModel
import com.valerasetrakov.simpleexample.adapters.event.event1.Event1ViewModel
import com.valerasetrakov.simpleexample.adapters.event.event2.Event2ViewModel
import com.valerasetrakov.simpleexample.adapters.event.event3.Event3ViewModel
import com.valerasetrakov.simpleexample.data.events.entity.EventType
import com.valerasetrakov.simpleexample.screen.base.ProjectFragmentViewModel
import javax.inject.Inject

class EventsFragmentViewModel @Inject constructor(application: Application, private val repository: EventsFragmentRepository)
    : ProjectFragmentViewModel(application) {

    val events = Transformations.map(repository.subsctibeToEvents()) { events ->
        events ?: emptyList<EventViewModel>()
        events.map { event ->
            val eventType = event.type
            when(eventType) {
                EventType.FirstType -> {
                    Event1ViewModel(id = event.id, title = event.title,
                        placeTitle = event.placeTitle, description = event.description,
                        image = event.image)
                }
                EventType.SecondType -> {
                    Event2ViewModel(id = event.id, title = event.title,
                        placeTitle = event.placeTitle, description = event.description,
                        image = event.image)
                }
                EventType.ThirdType -> {
                    Event3ViewModel(id = event.id, title = event.title,
                        placeTitle = event.placeTitle, description = event.description,
                        image = event.image)
                }
            }
        }
    }

    fun requestEvents() {
        invokeAsyncOperation {
            repository.requestEvents()
        }
    }

    override fun reload() {
        super.reload()
        requestEvents()
    }

}