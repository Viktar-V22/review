package com.data.voices

import android.content.res.AssetManager
import com.boundaries.core.speech.VoicesRepository
import com.boundaries.voices.Voice
import com.core.common.Gender
import com.core.common.Language
import com.core.common.Mapper
import com.core.common.toStrLowercase
import com.data.core.speech.Speech
import com.data.core.speech.VoiceStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.util.*
import com.google.cloud.texttospeech.v1.Voice as CloudVoice

class VoicesRepositoryImpl(
    private val assets: AssetManager,
    private val speech: Speech,
    private val toVoice: Mapper<CloudVoice, Voice>,
    private val store: VoiceStore
) : VoicesRepository {

    private companion object {
        private const val NAMES_FILE = "names.json"
        private val codes = listOf("en-GB", "en-US")
    }

    override suspend fun voice() = withContext(Dispatchers.IO) {
        store.voice() ?: voices().minByOrNull { it.name }!!.apply { store.store(this) }
    }

    override suspend fun voices() = withContext(Dispatchers.IO) {
        val cloudVoices = async { speech.voices(Language.ENGLISH.code) }
        val names = async { names() }

        toVoice.transform(
            cloudVoices.await().filter { codes.contains(it.languageCodesList.first()) },
            names.await()
        )
    }

    override fun select(voice: Voice) = store.store(voice)

    private fun names(): Map<Gender, List<String>> {
        val raw = assets.open(NAMES_FILE).use {
            it.bufferedReader().use { buffer -> buffer.readText() }
        }
        val names = Json.decodeFromString<List<ServerName>>(raw)

        return Hashtable<Gender, List<String>>().also { byGender ->
            Gender.values().forEach { gender ->
                byGender[gender] = names.filter { name -> name.gender == gender.toStrLowercase() }
                    .map { name -> name.name }
            }
        }
    }
}