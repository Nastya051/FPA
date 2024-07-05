package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.CancelEventUseCase
import kotlinx.coroutines.flow.Flow

class CancelEventUseCaseImpl(private val eventsRepository: EventsRepository): CancelEventUseCase {
    override fun execute(id: Int): Flow<Result> {
        return eventsRepository.cancelEvent(id = id)
    }
}