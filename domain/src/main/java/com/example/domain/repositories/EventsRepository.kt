package com.example.domain.repositories

import com.example.domain.models.Result
import com.example.domain.models.events.LeaveEvent
import com.example.domain.models.events.RegistrationForEvent
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun getActiveEvents(): Flow<Result>

    fun createEvent(): Flow<Result>//add model

    fun getEvent(id: Int): Flow<Result>

    fun cancelEvent(id: Int): Flow<Result>

    fun getUserEvents(id: Int): Flow<Result>

    fun eventRegistration(data: RegistrationForEvent): Flow<Result>

    fun leaveEvent(data: LeaveEvent): Flow<Result>

    fun getListParticipants(id: Int): Flow<Result>
}