package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.Result
import com.example.domain.models.auth.UserCode
import kotlinx.coroutines.flow.Flow

interface CheckRegistrationCodeUseCase {
    fun execute(code: UserCode): Flow<Result>
}