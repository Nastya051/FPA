package com.example.domain.usecases.events.interfaces

import com.example.domain.models.Result
import com.example.domain.models.events.LeaveEvent
import kotlinx.coroutines.flow.Flow

interface LeaveEventUseCase {
    fun execute(data: LeaveEvent): Flow<Result>
}