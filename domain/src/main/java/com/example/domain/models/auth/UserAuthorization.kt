package com.example.domain.models.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserAuthorization(
   @SerialName("login")
   val login: String,
   @SerialName ("password")
   val password: String
) {
//    fun toUserAuthUi() {
//        UserAuthorizationUi(login, password)
//    }
}
