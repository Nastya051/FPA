package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.models.events.RegistrationForEvent
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.EventRegistrationUseCase
import kotlinx.coroutines.flow.Flow

class EventRegistrationUseCaseImpl(private val eventsRepository: EventsRepository) :
    EventRegistrationUseCase {
    override fun execute(data: RegistrationForEvent): Flow<Result> {
        return eventsRepository.eventRegistration(data = data)
    }
}