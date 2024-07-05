package com.example.domain.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRecoveryPassword(
    @SerialName("userId")
    val userId: Int,
    @SerialName("password")
    val password: String
)
