package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserCode
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.CheckRegistrationCodeUseCase
import kotlinx.coroutines.flow.Flow

class CheckRegistrationCodeUseCaseImpl(private val authenticationRepository: AuthenticationRepository):
    CheckRegistrationCodeUseCase {

    override fun execute(code: UserCode): Flow<Result> {
        return authenticationRepository.checkRegistrationCode(code = code)
    }
}