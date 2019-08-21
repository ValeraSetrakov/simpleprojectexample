package com.valerasetrakov.simpleexample.adapters.event.event2

import com.valerasetrakov.simpleexample.adapters.event.EventViewModel

data class Event2ViewModel(
    val id: String,
    val title: String,
    val placeTitle: String,
    val description: String,
    val image: String
): EventViewModel