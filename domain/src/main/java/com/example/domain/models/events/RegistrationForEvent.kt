package com.example.domain.models.events

data class RegistrationForEvent(
    val userId: Int,
    val eventId: Int,
    val teamNumber: Int
)
