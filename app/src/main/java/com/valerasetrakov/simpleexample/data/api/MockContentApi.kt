package com.valerasetrakov.simpleexample.data.api

import com.valerasetrakov.simpleexample.data.events.entity.RequestEvent
import com.valerasetrakov.simpleexample.data.events.entity.ResponseEvent
import com.valerasetrakov.simpleexample.data.profile.entity.RequestProfile
import com.valerasetrakov.simpleexample.data.profile.entity.ResponseProfile
import kotlinx.coroutines.delay

class MockContentApi : ContentApi {

    private val responseEvents = listOf<ResponseEvent>(
        ResponseEvent(
            id = "1",
            title = "Title 1",
            placeTitle = "Random address 1",
            type = 0,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            image = "https://www.lawliberty.org/wp-content/uploads/2017/12/AdobeStock_105994137-1024x680.jpeg"
        ),
        ResponseEvent(
            id = "2",
            title = "Title 2",
            placeTitle = "Random address",
            type = 1,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            image = "https://cdn.pixabay.com/photo/2017/04/02/19/23/environment-2196690_960_720.jpg"
        ),
        ResponseEvent(
            id = "3",
            title = "Title 3",
            placeTitle = "Random address",
            type = 2,
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTfPTjWYlUKHHj4K6RBjiYB8tkalhTwA7-DYfBSJaC3UwZgFs3N"
        )
    )

    override suspend fun requestProfile(request: RequestProfile): ResponseProfile {
        delay(2000)
        val id = request.id
        return ResponseProfile(
            id = id,
            name = "Сетраков Валерий Владимирович",
            age = 22,
            study = "Южный Федеральный Университет",
            avatar = "https://sun9-41.userapi.com/c845217/v845217990/166118/c1FHcwIo6d4.jpg"
        )
    }

    override suspend fun requestEvent(request: RequestEvent): ResponseEvent {
        delay(3000)
        val id = request.id
        return responseEvents.find { it.id == id }!!
    }

    override suspend fun requestEvents(): List<ResponseEvent> {
        delay(3000)
        return responseEvents
    }
}