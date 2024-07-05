package com.example.domain.usecases.auth.impl

import com.example.domain.models.auth.UserRegistrationStepOne
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.CheckUserRegistrationStepOneUseCase
import kotlinx.coroutines.flow.Flow
import com.example.domain.models.Result

class CheckUserRegistrationStepOneUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    CheckUserRegistrationStepOneUseCase {

    override fun execute(user: UserRegistrationStepOne): Flow<Result> {
        return authenticationRepository.checkUserForRegistrationStepOne(user = user)
    }
}