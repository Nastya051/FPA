package com.example.domain.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserPhone(
    @SerialName("phone")
    val phone: String
)
