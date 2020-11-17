package com.data.core.speech

import android.util.Log
import com.boundaries.core.speech.SpeechRepository
import com.boundaries.voices.Voice
import com.data.core.sound.SoundPlayer
import com.google.cloud.texttospeech.v1.AudioEncoding
import com.google.cloud.texttospeech.v1.SsmlVoiceGender
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SpeechRepositoryImpl(
    private val speech: Speech,
    private val player: SoundPlayer,
    private val store: SpeechStore
) : SpeechRepository {

    private companion object {
        private val format = AudioEncoding.MP3
    }

    override suspend fun speech(text: String, voice: Voice) = withContext(Dispatchers.IO) {
        Log.d("SPEECH", "$voice: $text")
        if (text.isNotBlank()) {
            val path = store.filePath(voice.name, text)
            val speechPath = if (path == null) {
                val speech = synthesize(text, voice)
                store.store(voice.rawName, text, speech)
            } else path
            player.load(speechPath).volume(1f, 1f).play()
        }
    }

    private suspend fun synthesize(text: String, voice: Voice): ByteArray {
        return if (text.isBlank()) ByteArray(0) else SsmlVoiceGender.forNumber(voice.rawGender)
            .let { speech.synthesize(text, voice.rawName, voice.code, it, format) }
    }
}