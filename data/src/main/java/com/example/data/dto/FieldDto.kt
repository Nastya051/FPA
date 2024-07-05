package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse (
    val status: Boolean,
    val code: Int,
    val message: String,
    val time: String,
    //val data: List<FieldDto>?
)

@Serializable
data class FieldDto (
//    @SerialName("ID")
//    val id: String,
//
//    @SerialName("UF_CREATED")
//    val ufCreated: String,
//
//    @SerialName("UF_STATUS")
//    val ufStatus: UFAREATYPEClass,

    @SerialName("UF_NAME")
    val ufName: String,

    @SerialName("UF_TOWN")
    val ufTown: Int,

    @SerialName("UF_ADDRESS")
    val ufAddress: String,

    @SerialName("UF_OPENING")
    val ufOpening: String,

    @SerialName("UF_CLOSING")
    val ufClosing: String,

    @SerialName("UF_PHONE")
    val ufPhone: String,

//    @SerialName("UF_NEAR_METRO")
//    val ufNearMetro: UFNEARMETROClass?,

    @SerialName("UF_NEAR_METRO")
    val ufNearMetro: Int?,

    @SerialName("UF_SITE")
    val ufSite: String,

    @SerialName("UF_DESCRIPTION")
    val ufDescription: String,

    @SerialName("UF_PLAYER_CAPACITY")
    val ufPlayerCapacity: Long,

    @SerialName("UF_LENGTH")
    val ufLength: Int?,

    @SerialName("UF_WIDTH")
    val ufWidth: Int?,

    @SerialName("UF_AREA_TYPE")
    val ufAreaType: Int,

    @SerialName("UF_LIGHTING")
    val ufLighting: Int,

    @SerialName("UF_SHOWER")
    val ufShower: Boolean?,

    @SerialName("UF_IMAGES")
    val ufImages: List<String>,

    @SerialName("UF_COVER")
    val ufCover: Int,

    @SerialName("UF_DRESSING_ROOMS")
    val ufDressingRooms: Int,

    @SerialName("UF_STANDS")
    val ufStands: Int?,

//    @SerialName("UF_USER")
//    val ufUser: Long,
//
//    @SerialName("UF_RATING")
//    val ufRating: Float,
//
//    @SerialName("UF_TAGS")
//    val ufTags: List<UfTag>,
//
//    @SerialName("FAVORITE")
//    val favorite: Boolean
)

@Serializable
data class UFAREATYPEClass (
    @SerialName("ID")
    val id: Long,

    @SerialName("VALUE")
    val value: String
)

@Serializable
data class UfImage (
    @SerialName("SRC")
    val src: String
)

@Serializable
data class UFNEARMETROClass (
    @SerialName("ID")
    val id: Long,

    @SerialName("UF_NAME")
    val ufName: String
)

@Serializable
data class UfTag (
    @SerialName("NAME")
    val name: String,

    @SerialName("COUNT")
    val count: Long
)