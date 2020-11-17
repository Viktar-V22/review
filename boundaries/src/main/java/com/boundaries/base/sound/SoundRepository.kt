package com.boundaries.base.sound

interface SoundRepository {

    suspend fun play(sound: Sound)
}