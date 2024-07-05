package com.example.data.services

import com.example.domain.models.events.LeaveEvent
import com.example.domain.models.events.RegistrationForEvent
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface ApiServiceEvents {
    suspend fun getActiveEvents(): HttpResponse

    suspend fun createEvent(): HttpResponse

    suspend fun getEvent(id: Int): HttpResponse

    suspend fun cancelEvent(id: Int): HttpResponse

    suspend fun getUserEvents(id: Int): HttpResponse

    suspend fun eventRegistration(data: RegistrationForEvent): HttpResponse

    suspend fun leaveEvent(data: LeaveEvent): HttpResponse

    suspend fun getListParticipants(id: Int): HttpResponse
}

class ApiServiceEventsImpl(private val client: HttpClient): ApiServiceEvents{

    private val GET_ACTIVE_EVENTS = "events/getActiveEvents/"
    private val CREATE_EVENT = "events/createEvent/"
    private val GET_EVENT = "events/getEvent/"
    private val CANCEL_EVENT = "events/cancelEvent/"
    private val GET_USER_EVENTS = "events/userEvents/"
    private val EVENT_REGISTRATION = "events/eventRegistration/"
    private val LEAVE_EVENT = "events/leaveEvent/"
    private val GET_EVENT_PARTICIPANTS = "events/eventUsers/"

    override suspend fun getActiveEvents(): HttpResponse {
        return client.get(GET_ACTIVE_EVENTS){
            contentType(ContentType.Application.Json)
        }
    }

    override suspend fun createEvent(): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getEvent(id: Int): HttpResponse {
        return client.get(GET_ACTIVE_EVENTS){
            contentType(ContentType.Application.Json)
            parameter("event_id", id)
        }
    }

    override suspend fun cancelEvent(id: Int): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getUserEvents(id: Int): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun eventRegistration(data: RegistrationForEvent): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun leaveEvent(data: LeaveEvent): HttpResponse {
        TODO("Not yet implemented")
    }

    override suspend fun getListParticipants(id: Int): HttpResponse {
        TODO("Not yet implemented")
    }

}