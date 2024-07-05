package com.example.domain.usecases.auth.interfaces

import com.example.domain.models.auth.UserAuthorization
import kotlinx.coroutines.flow.Flow
import com.example.domain.models.Result

interface CheckUserForAuthorizationUseCase {
    fun execute(user: UserAuthorization): Flow<Result>
}