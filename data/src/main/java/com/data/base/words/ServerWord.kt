package com.data.base.words

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerWord(
    @SerialName("en") val en: String,
    @SerialName("ru") val ru: Set<String>,
    @SerialName("transcription") val transcription: String
)