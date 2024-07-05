package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.models.events.LeaveEvent
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.LeaveEventUseCase
import kotlinx.coroutines.flow.Flow

class LeaveEventUseCaseImpl(private val eventsRepository: EventsRepository): LeaveEventUseCase {
    override fun execute(data: LeaveEvent): Flow<Result> {
        return eventsRepository.leaveEvent(data = data)
    }
}