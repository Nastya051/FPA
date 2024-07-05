package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.GetEventUseCase
import kotlinx.coroutines.flow.Flow

class GetEventUseCaseImpl(private val eventsRepository: EventsRepository): GetEventUseCase {
    override fun execute(id: Int): Flow<Result> {
        return eventsRepository.getEvent(id = id)
    }
}