package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.CreateEventUseCase
import kotlinx.coroutines.flow.Flow

class CreateEventUseCaseImpl(private val eventsRepository: EventsRepository): CreateEventUseCase {
    override fun execute(): Flow<Result> {
        return eventsRepository.createEvent()
    }
}