package com.data.irregular

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerVerb(
    @SerialName("sound") val sound: String,
    @SerialName("transcription") val transcription: String,
    @SerialName("word") val word: String
)