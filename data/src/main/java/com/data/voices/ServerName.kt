package com.data.voices

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerName(
    @SerialName("name") val name: String,
    @SerialName("gender") val gender: String
)