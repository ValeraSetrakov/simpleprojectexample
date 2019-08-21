package com.valerasetrakov.simpleexample.adapters.event.event1

import com.valerasetrakov.simpleexample.adapters.event.EventViewModel

data class Event1ViewModel(
    val id: String,
    val title: String,
    val placeTitle: String,
    val description: String,
    val image: String
): EventViewModel