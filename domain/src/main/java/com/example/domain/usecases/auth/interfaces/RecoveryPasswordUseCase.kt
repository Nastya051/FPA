package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.Result
import com.example.domain.models.auth.UserRecoveryPassword
import com.example.domain.models.auth.UserRegistration
import kotlinx.coroutines.flow.Flow

interface RecoveryPasswordUseCase {
    fun execute(user: UserRecoveryPassword): Flow<Result>
}