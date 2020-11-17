package com.data.base.sound

import com.boundaries.base.sound.Sound
import com.boundaries.base.sound.SoundRepository
import com.data.core.sound.SoundPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SoundRepositoryImpl @Inject constructor(private val player: SoundPlayer) : SoundRepository {

    override suspend fun play(sound: Sound) = withContext(Dispatchers.IO) {
        player.load(sound.rawRes()).volume(1f, 1f).play()
    }
}