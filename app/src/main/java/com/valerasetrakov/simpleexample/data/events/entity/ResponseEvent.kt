package com.valerasetrakov.simpleexample.data.events.entity

data class ResponseEvent(
    val id: String,
    val title: String,
    val placeTitle: String,
    val type: Int,
    val description: String,
    val image: String
)