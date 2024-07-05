package com.example.domain.models.events

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Events(
    @SerialName("status")
    val status: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("time")
    val time: String,
    @SerialName("data")
    val data: List<EventData>
)

@Serializable
data class Event(
    @SerialName("status")
    val status: Boolean,
    @SerialName("code")
    val code: String,
    @SerialName("message")
    val message: String,
    @SerialName("time")
    val time: String,
    @SerialName("data")
    val data: EventData
)

@Serializable
data class EventData (
    @SerialName("ID")
    val id: String,
    @SerialName("UF_DATE")
    val ufDate: String,
    @SerialName("UF_START_TIME")
    val ufStartTime: String,
    @SerialName("UF_END_TIME")
    val ufEndTime: String,
    @SerialName("UF_GENDER")
    val ufGender: String,
    @SerialName("UF_PRICE")
    val ufPrice: String?,
    @SerialName("UF_NAME")
    val ufName: String,
    @SerialName("UF_AD_INFO")
    val ufAdInfo: String,
    @SerialName("UF_ACCESS")
    val ufAccess: String,
    @SerialName("UF_PLAYERS_COUNT")
    val ufPlayersCount: String?,
    @SerialName("UF_MIN_PLAYERS")
    val ufMinPlayers: String?,
    @SerialName("UF_STATUS")
    val ufStatus: Int,
    @SerialName("UF_FIELD")
    val ufField: String?,
    @SerialName("UF_HOST_ID")
    val ufHostID: Int
)