package com.domain.core.speech

import com.boundaries.core.speech.SpeechRepository
import com.boundaries.core.speech.VoicesRepository
import com.boundaries.voices.Voice
import javax.inject.Inject

class SpeechCaseImpl @Inject constructor(
    private val speech: SpeechRepository,
    private val voices: VoicesRepository
) : SpeechCase {

    override suspend fun speech(text: String) = speech(text, voices.voice())

    override suspend fun speech(text: String, voice: Voice) = speech.speech(text, voice)
}