package com.valerasetrakov.simpleexample.adapters.event.event3

import com.valerasetrakov.simpleexample.adapters.event.EventViewModel

data class Event3ViewModel(
    val id: String,
    val title: String,
    val placeTitle: String,
    val description: String,
    val image: String
): EventViewModel