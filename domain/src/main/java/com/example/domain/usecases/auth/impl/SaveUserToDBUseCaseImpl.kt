package com.example.domain.usecases.auth.impl

import com.example.domain.models.Result
import com.example.domain.models.auth.UserRegistration
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.SaveUserToDBUseCase
import kotlinx.coroutines.flow.Flow

class SaveUserToDBUseCaseImpl(private val authenticationRepository: AuthenticationRepository):
    SaveUserToDBUseCase {

    override fun execute(user: UserRegistration): Flow<Result> {
        return authenticationRepository.saveUserToDB(user = user)
    }
}