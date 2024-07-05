package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserRecoveryPassword
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.RecoveryPasswordUseCase
import kotlinx.coroutines.flow.Flow

class RecoveryPasswordUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    RecoveryPasswordUseCase {
    override fun execute(user: UserRecoveryPassword): Flow<Result> {
        return authenticationRepository.recoveryPassword(user = user)
    }
}