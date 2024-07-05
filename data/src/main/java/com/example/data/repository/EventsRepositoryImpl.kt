package com.example.data.repository

import com.example.data.services.ApiServiceEvents
import com.example.domain.models.CommonAnswer
import com.example.domain.models.Result
import com.example.domain.models.events.Event
import com.example.domain.models.events.Events
import com.example.domain.models.events.LeaveEvent
import com.example.domain.models.events.RegistrationForEvent
import com.example.domain.repositories.EventsRepository
import io.ktor.client.call.body
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException

class EventsRepositoryImpl(private val apiServiceEvents: ApiServiceEvents): EventsRepository {
    override fun getActiveEvents(): Flow<Result> {
        return flow {
            val response = apiServiceEvents.getActiveEvents()
            try {
                when (response.status) {
                    HttpStatusCode.OK -> {
                        try {
                            emit(Result.Success(value = response.body<Events>()))
                        } catch (e: SerializationException) {
                            emit(Result.ErrorNetwork(value = response.body<CommonAnswer>()
                                .toCommonAnswerUi()))
                        }
                    }
                    else -> { emit(Result.Error(value = Exception("error"))) }
                }
            } catch (e: Exception) {
                emit(Result.Error(value = e))
            }
        }
    }

    override fun createEvent(): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun getEvent(id: Int): Flow<Result> {
        return flow {
            val response = apiServiceEvents.getEvent(id = id)
            try {
                when (response.status) {
                    HttpStatusCode.OK -> {
                        try {
                            emit(Result.Success(value = response.body<Event>()))
                        } catch (e: SerializationException) {
                            emit(Result.ErrorNetwork(value = response.body<CommonAnswer>()
                                .toCommonAnswerUi()))
                        }
                    }
                    else -> { emit(Result.Error(value = Exception("error"))) }
                }
            } catch (e: Exception) {
                emit(Result.ErrorNetwork(value = response.body<CommonAnswer>()
                    .toCommonAnswerUi()))
            }
        }
    }

    override fun cancelEvent(id: Int): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun getUserEvents(id: Int): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun eventRegistration(data: RegistrationForEvent): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun leaveEvent(data: LeaveEvent): Flow<Result> {
        TODO("Not yet implemented")
    }

    override fun getListParticipants(id: Int): Flow<Result> {
        TODO("Not yet implemented")
    }
}