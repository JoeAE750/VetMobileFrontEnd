/*package com.fisi.vetmobile.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,
    val message: String,
    val status: Int
)
*/

package com.fisi.vetmobile.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("access_token")
    val accessToken: String,
    val message: String,
    val status: Int,
    val id_usuario: String
)
