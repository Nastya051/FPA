package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.GetUserEventsUseCase
import kotlinx.coroutines.flow.Flow

class GetUserEventsUseCaseImpl(private val eventsRepository: EventsRepository): GetUserEventsUseCase {
    override fun execute(id: Int): Flow<Result> {
        return eventsRepository.getUserEvents(id = id)
    }
}