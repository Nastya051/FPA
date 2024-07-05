package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.Result
import com.example.domain.models.auth.UserEmail
import kotlinx.coroutines.flow.Flow

interface SendCodeToEmailUseCase {
    fun execute(email: UserEmail): Flow<Result>
}