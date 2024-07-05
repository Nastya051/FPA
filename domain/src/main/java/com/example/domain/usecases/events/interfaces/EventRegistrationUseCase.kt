package com.example.domain.usecases.events.interfaces

import com.example.domain.models.Result
import com.example.domain.models.events.RegistrationForEvent
import kotlinx.coroutines.flow.Flow

interface EventRegistrationUseCase {
    fun execute(data: RegistrationForEvent): Flow<Result>
}