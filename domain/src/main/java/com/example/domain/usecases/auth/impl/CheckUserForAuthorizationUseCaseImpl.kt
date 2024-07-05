package com.example.domain.usecases.auth.impl

import com.example.domain.models.auth.UserAuthorization
import com.example.domain.repositories.AuthenticationRepository
import com.example.domain.usecases.auth.interfaces.CheckUserForAuthorizationUseCase
import kotlinx.coroutines.flow.Flow
import com.example.domain.models.Result

class CheckUserForAuthorizationUseCaseImpl(private val authenticationRepository: AuthenticationRepository):
    CheckUserForAuthorizationUseCase {

    override fun execute(user: UserAuthorization): Flow<Result> {
        return authenticationRepository.checkUserForAuthorization(user = user)
    }
}