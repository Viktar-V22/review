package com.data.base.images

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerImage(
    @SerialName("ru") val ru: Set<String>,
    @SerialName("image_url") val imageUrl: String
)