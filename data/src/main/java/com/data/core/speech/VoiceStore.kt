package com.data.core.speech

import com.boundaries.voices.Voice

interface VoiceStore {

    fun store(voice: Voice)

    fun voice(): Voice?
}