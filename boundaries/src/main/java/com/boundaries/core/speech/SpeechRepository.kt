package com.boundaries.core.speech

import com.boundaries.voices.Voice

interface SpeechRepository {

    suspend fun speech(text: String, voice: Voice)
}