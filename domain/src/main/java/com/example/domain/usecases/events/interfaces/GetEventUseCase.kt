package com.example.domain.usecases.events.interfaces

import com.example.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface GetEventUseCase {
    fun execute(id: Int): Flow<Result>
}