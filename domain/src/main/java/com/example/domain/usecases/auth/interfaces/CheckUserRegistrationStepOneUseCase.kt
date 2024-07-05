package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.auth.UserRegistrationStepOne
import kotlinx.coroutines.flow.Flow
import com.example.domain.models.Result

interface CheckUserRegistrationStepOneUseCase {
    fun execute(user: UserRegistrationStepOne): Flow<Result>
}