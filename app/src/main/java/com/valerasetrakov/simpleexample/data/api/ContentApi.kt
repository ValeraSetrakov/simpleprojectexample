package com.valerasetrakov.simpleexample.data.api

import com.valerasetrakov.simpleexample.data.events.entity.RequestEvent
import com.valerasetrakov.simpleexample.data.events.entity.ResponseEvent
import com.valerasetrakov.simpleexample.data.profile.entity.RequestProfile
import com.valerasetrakov.simpleexample.data.profile.entity.ResponseProfile
import retrofit2.http.Body
import retrofit2.http.GET

interface ContentApi {

    @GET("/api/profile")
    suspend fun requestProfile(@Body request: RequestProfile): ResponseProfile

    @GET("/api/event")
    suspend fun requestEvent(@Body request: RequestEvent): ResponseEvent

    @GET("/api/events")
    suspend fun requestEvents(): List<ResponseEvent>
}