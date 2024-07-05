package com.example.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonAnswer(
    @SerialName("status")
    val status: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("time")
    val time: String
) {
    fun toCommonAnswerUi() {
        CommonAnswerUi(status = status, code = code, message = message, time = time)
    }
}

