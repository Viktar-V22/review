package com.data.core.sound.soundpool

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.SoundPool
import com.boundaries.core.sound.SoundPoolError
import com.data.core.sound.Equalizer
import com.data.core.sound.SoundPlayer
import com.data.core.sound.Source
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SoundPoolPlayer(
    private val context: Context,
    private val maxStreams: Int,
    private val attrs: AudioAttributes
) : SoundPlayer, StreamSoundPlayer {

    private companion object {
        private const val SUCCESS_LOADED = 0
    }

    private var soundPool: SoundPool? = null
    private val equalizers = HashMap<Source, Equalizer>()
    private val sampleIds = HashMap<Source, Int>()
    private val streamIds = HashMap<Source, Int>()

    @Synchronized
    private fun soundPool() = soundPool ?: SoundPool.Builder()
        .setMaxStreams(maxStreams)
        .setAudioAttributes(attrs)
        .build().apply { soundPool = this }

    override suspend fun load(resourceId: Int) = load(Source(resourceId = resourceId))

    override suspend fun load(path: String) = load(Source(path = path))

    override suspend fun load(descr: AssetFileDescriptor): Equalizer {
        return load(Source(descriptor = descr))
    }

    private suspend fun load(source: Source): Equalizer {
        val soundPool = soundPool()

        val sId = source.resourceId?.let { soundPool.load(context, it, 1) }
            ?: source.descriptor?.let { soundPool.load(it, 1) }
            ?: source.path?.let { soundPool.load(it, 1) }
            ?: throw IllegalStateException("empty source")

        val equalizer = equalizers[source] ?: SoundPoolEqualizer(source)
            .apply { equalizers[source] = this }
        sampleIds[source] = sId

        return suspendCoroutine { cont ->
            soundPool().setOnLoadCompleteListener { _, sampleId, status ->
                if (sampleId == sId) if (status == SUCCESS_LOADED) cont.resume(equalizer) else
                    cont.resumeWithException(SoundPoolError())
            }
        }
    }

    override fun stop(resourceId: Int) = stop(Source(resourceId = resourceId))

    override fun stop(path: String) = stop(Source(path = path))

    override fun stop(descriptor: AssetFileDescriptor) = stop(Source(descriptor = descriptor))

    override fun stopAll() = streamIds.values.forEach { soundPool().stop(it) }

    private fun stop(source: Source) = streamIds[source]?.let { soundPool().stop(it) } ?: Unit

    override fun pause(resourceId: Int) = pause(Source(resourceId = resourceId))

    override fun pause(path: String) = pause(Source(path = path))

    override fun pause(descriptor: AssetFileDescriptor) = pause(Source(descriptor = descriptor))

    override fun pauseAll() = soundPool().autoPause()

    private fun pause(source: Source) = streamIds[source]?.let { soundPool().pause(it) } ?: Unit

    override fun resume(resourceId: Int) = resume(Source(resourceId = resourceId))

    override fun resume(path: String) = resume(Source(path = path))

    override fun resume(descriptor: AssetFileDescriptor) = resume(Source(descriptor = descriptor))

    override fun resumeAll() = soundPool().autoResume()

    private fun resume(source: Source) = streamIds[source]?.let { soundPool().resume(it) } ?: Unit

    override fun release() {
        soundPool?.release()
        soundPool = null
        sampleIds.clear()
    }

    private inner class SoundPoolEqualizer(
        private val source: Source
    ) : Equalizer, RepeatEqualizer, StreamEqualizer {

        private var leftVolume = 1f
        private var rightVolume = 1f
        private var priority = 0
        private var repeat = 0
        private var rate = 1f

        override fun volume(left: Float, right: Float): Equalizer {
            return also { leftVolume = left; rightVolume = right }
        }

        override fun leftVolume(volume: Float) = also { leftVolume = volume }

        override fun rightVolume(volume: Float) = also { rightVolume = volume }

        override fun looping() = also { repeat = -1 }

        override fun priority(priority: Int) = also { this.priority }

        override fun repeat(count: Int) = also { repeat = count }

        override fun endless() = also { repeat = -1 }

        override fun rate(rate: Float) = also { this.rate = rate }

        override suspend fun play() {
            val sampleId = sampleIds[source] ?: throw IllegalStateException("source: $source")
            val stream = soundPool().play(sampleId, leftVolume, rightVolume, priority, repeat, rate)
            streamIds[source] = stream
        }
    }
}