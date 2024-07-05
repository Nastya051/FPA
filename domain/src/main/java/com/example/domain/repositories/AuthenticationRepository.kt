package com.example.domain.repositories

import com.example.domain.models.auth.UserAuthorization
import com.example.domain.models.auth.UserRecoveryPassword
import com.example.domain.models.auth.UserRegistration
import com.example.domain.models.auth.UserRegistrationStepOne
import kotlinx.coroutines.flow.Flow
import com.example.domain.models.Result
import com.example.domain.models.auth.UserCode
import com.example.domain.models.auth.UserEmail
import com.example.domain.models.auth.UserPhone

interface AuthenticationRepository {
     fun checkUserForAuthorization(user: UserAuthorization): Flow<Result>

    fun checkUserForRegistrationStepOne(user: UserRegistrationStepOne): Flow<Result>

    fun saveUserToDB(user: UserRegistration): Flow<Result>

    fun sendCodeToPhone(phone: UserPhone): Flow<Result>

    fun sendCodeToEmail(email: UserEmail): Flow<Result>

    fun checkRegistrationCode(code: UserCode): Flow<Result>

    fun checkRecoveryCode(code: UserCode): Flow<Result>

    fun recoveryPassword(user: UserRecoveryPassword): Flow<Result>

}