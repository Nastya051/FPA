package com.example.data.services

import com.example.domain.models.auth.UserAuthorization
import com.example.domain.models.auth.UserCode
import com.example.domain.models.auth.UserEmail
import com.example.domain.models.auth.UserPhone
import com.example.domain.models.auth.UserRecoveryPassword
import com.example.domain.models.auth.UserRegistration
import com.example.domain.models.auth.UserRegistrationStepOne
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType


interface ApiServiceAuthentication {
    suspend fun authorization(userData: UserAuthorization): HttpResponse
    suspend fun registrationStepOne(userData: UserRegistrationStepOne): HttpResponse
    suspend fun registration(userData: UserRegistration): HttpResponse
    suspend fun sendRegistrationCode(phone: UserPhone): HttpResponse
    suspend fun sendRecoveryCode(email: UserEmail): HttpResponse
    suspend fun checkRegistrationCode(code: UserCode): HttpResponse
    suspend fun checkRecoveryCode(code: UserCode): HttpResponse
    suspend fun updatePassword(userData: UserRecoveryPassword): HttpResponse
}

class ApiServiceAuthenticationImpl(private val client: HttpClient) : ApiServiceAuthentication {

    private val AUTHORIZATION = "auth/login/"
    private val REGISTRATION_STEP_ONE = "auth/registerUserStep1/"
    private val REGISTRATION = "auth/register/"
    private val SEND_REGISTRATION_CODE = "auth/sendRegistrationCode/"
    private val SEND_RECOVERY_CODE = "auth/sendRecoveryCode/"
    private val CHECK_REGISTRATION_CODE = "auth/checkRegistrationCode/"
    private val CHECK_RECOVERY_CODE = "auth/checkRecoveryCode/"
    private val UPDATE_PASSWORD = "auth/updatePassword/"

    override suspend fun authorization(userData: UserAuthorization): HttpResponse {
        return client.post(AUTHORIZATION) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }
    }

    override suspend fun registrationStepOne(userData: UserRegistrationStepOne): HttpResponse {
        return client.post(REGISTRATION_STEP_ONE) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }
    }

    override suspend fun registration(userData: UserRegistration): HttpResponse {
        return client.post(REGISTRATION) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }
    }


    override suspend fun sendRegistrationCode(phone: UserPhone): HttpResponse {
        return client.post(SEND_REGISTRATION_CODE) {
            contentType(ContentType.Application.Json)
            setBody(phone)
        }
    }

    override suspend fun sendRecoveryCode(email: UserEmail): HttpResponse {
        return client.post(SEND_RECOVERY_CODE) {
            contentType(ContentType.Application.Json)
            setBody(email)
        }
    }

    override suspend fun checkRegistrationCode(code: UserCode): HttpResponse {
        return client.post(CHECK_REGISTRATION_CODE) {
            contentType(ContentType.Application.Json)
            setBody(code)
        }
    }

    override suspend fun checkRecoveryCode(code: UserCode): HttpResponse {
        return client.post(CHECK_RECOVERY_CODE) {
            contentType(ContentType.Application.Json)
            setBody(code)
        }
    }

    override suspend fun updatePassword(userData: UserRecoveryPassword): HttpResponse {
        return client.post(UPDATE_PASSWORD) {
            contentType(ContentType.Application.Json)
            setBody(userData)
        }
    }
}