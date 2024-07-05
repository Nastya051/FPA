package com.example.domain.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserEmail(
    @SerialName("email")
    val email: String
)
