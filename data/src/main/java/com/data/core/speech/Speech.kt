package com.data.core.speech

import com.google.cloud.texttospeech.v1.AudioEncoding
import com.google.cloud.texttospeech.v1.SsmlVoiceGender
import com.google.cloud.texttospeech.v1.Voice

interface Speech {

    suspend fun synthesize(
        text: String,
        voice: String,
        langCode: String,
        gender: SsmlVoiceGender,
        format: AudioEncoding
    ): ByteArray

    suspend fun voices(langCode: String): List<Voice>
}