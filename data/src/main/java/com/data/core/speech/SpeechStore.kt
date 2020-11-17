package com.data.core.speech

interface SpeechStore {

    suspend fun store(voice: String, text: String, byteArray: ByteArray): String

    suspend fun filePath(voice: String, text: String): String?
}