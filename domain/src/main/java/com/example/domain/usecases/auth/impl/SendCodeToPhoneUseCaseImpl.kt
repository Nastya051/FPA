package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserPhone
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.SendCodeToPhoneUseCase
import kotlinx.coroutines.flow.Flow

class SendCodeToPhoneUseCaseImpl(private val authenticationRepository: AuthenticationRepository) :
    SendCodeToPhoneUseCase {

    override fun execute(phone: UserPhone): Flow<Result> {
        return authenticationRepository.sendCodeToPhone(phone = phone)
    }
}