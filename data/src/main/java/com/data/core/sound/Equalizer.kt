package com.data.core.sound

interface Equalizer {

    fun volume(left: Float, right: Float): Equalizer

    fun leftVolume(volume: Float): Equalizer

    fun rightVolume(volume: Float): Equalizer

    fun looping(): Equalizer

    fun rate(rate: Float): Equalizer

    suspend fun play()
}