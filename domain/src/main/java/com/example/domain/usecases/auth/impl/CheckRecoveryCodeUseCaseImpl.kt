package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserCode
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.CheckRecoveryCodeUseCase
import kotlinx.coroutines.flow.Flow

class CheckRecoveryCodeUseCaseImpl(private val authenticationRepository: AuthenticationRepository):
    CheckRecoveryCodeUseCase {
    override fun execute(code: UserCode): Flow<Result> {
        return authenticationRepository.checkRecoveryCode(code = code)
    }
}