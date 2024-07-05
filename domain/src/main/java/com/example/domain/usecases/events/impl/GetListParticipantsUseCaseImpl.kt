package com.example.domain.usecases.events.impl

import com.example.domain.models.Result
import com.example.domain.repositories.EventsRepository
import com.example.domain.usecases.events.interfaces.GetListParticipantsUseCase
import kotlinx.coroutines.flow.Flow

class GetListParticipantsUseCaseImpl(private val eventsRepository: EventsRepository) :
    GetListParticipantsUseCase {
    override fun execute(id: Int): Flow<Result> {
        return eventsRepository.getListParticipants(id = id)
    }
}