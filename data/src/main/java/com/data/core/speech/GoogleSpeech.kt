package com.data.core.speech

import com.google.api.gax.core.CredentialsProvider
import com.google.cloud.texttospeech.v1.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GoogleSpeech @Inject constructor(private val provider: CredentialsProvider) : Speech {

    private val speech: TextToSpeechClient by lazy {
        val settings = TextToSpeechSettings.newBuilder()
            .setCredentialsProvider { provider.credentials }
            .build()

        TextToSpeechClient.create(settings)
    }

    override suspend fun synthesize(
        text: String,
        voice: String,
        langCode: String,
        gender: SsmlVoiceGender,
        format: AudioEncoding
    ): ByteArray = withContext(Dispatchers.IO) {
        val input = SynthesisInput.newBuilder()
            .setText(text)
            .build()

        val voiceParams = VoiceSelectionParams.newBuilder()
            .setName(voice)
            .setLanguageCode(langCode)
            .setSsmlGender(gender)
            .build()

        val audio = AudioConfig.newBuilder()
            .setAudioEncoding(format)
            .setVolumeGainDb(16.0)
            .build()

        speech.synthesizeSpeech(input, voiceParams, audio).audioContent.toByteArray()
    }

    override suspend fun voices(langCode: String): List<Voice> = withContext(Dispatchers.IO) {
        val request = ListVoicesRequest.newBuilder()
            .setLanguageCode(langCode)
            .build()

        speech.listVoices(request).voicesList
    }
}