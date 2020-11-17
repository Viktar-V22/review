package com.boundaries.core.speech

import com.boundaries.voices.Voice

interface VoicesRepository {

    suspend fun voice(): Voice

    suspend fun voices(): List<Voice>

    fun select(voice: Voice)
}