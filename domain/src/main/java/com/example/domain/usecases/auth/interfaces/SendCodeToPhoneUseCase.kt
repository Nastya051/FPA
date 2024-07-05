package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.Result
import com.example.domain.models.auth.UserPhone
import kotlinx.coroutines.flow.Flow

interface SendCodeToPhoneUseCase {
    fun execute(phone: UserPhone): Flow<Result>
}