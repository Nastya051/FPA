package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserEmail
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.SendCodeToEmailUseCase
import kotlinx.coroutines.flow.Flow

class SendCodeToEmailUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    SendCodeToEmailUseCase {
    override fun execute(email: UserEmail): Flow<Result> {
        return authenticationRepository.sendCodeToEmail(email = email)
    }
}