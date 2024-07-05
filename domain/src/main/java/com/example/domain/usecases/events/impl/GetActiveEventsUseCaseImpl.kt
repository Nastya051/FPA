package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.GetActiveEventsUseCase
import kotlinx.coroutines.flow.Flow

class GetActiveEventsUseCaseImpl(private val eventsRepository: EventsRepository):
    GetActiveEventsUseCase {
    override fun execute(): Flow<Result> {
        return eventsRepository.getActiveEvents()
    }
}