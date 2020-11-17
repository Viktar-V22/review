package com.data.irregular

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ServerIrregular(
    @SerialName("infinitive") val infinitive: ServerVerb,
    @SerialName("past") val past: ServerVerb,
    @SerialName("past_participle") val pastParticiple: ServerVerb,
    @SerialName("ru") val ru: Set<String>
)