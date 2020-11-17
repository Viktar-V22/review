package com.domain.core.speech

import com.boundaries.voices.Voice

interface SpeechCase {

    suspend fun speech(text: String)

    suspend fun speech(text: String, voice: Voice)
}