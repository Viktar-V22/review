package com.data.core.sound.media

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.PlaybackParams
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.boundaries.core.sound.MediaPlayerError
import com.core.android.uriFromRaw
import com.data.core.sound.Equalizer
import com.data.core.sound.SoundPlayer
import com.data.core.sound.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SoundMediaPlayer(
    private val context: Context,
    private val attrs: AudioAttributes
) : SoundPlayer {

    private var player: MediaPlayer? = null

    private val equalizer: Equalizer by lazy { SoundMediaEqualizer() }

    private var source = LoadSource.empty

    override suspend fun load(resourceId: Int) = withContext(Dispatchers.IO) {
        synchronized(source) {
            if (source.source.resourceId != resourceId || !source.loaded) {
                source = LoadSource(Source(resourceId = resourceId), false)
                prepare { mp -> mp.setDataSource(context, context.uriFromRaw(resourceId)) }
            }
        }
        equalizer
    }

    override suspend fun load(path: String) = withContext(Dispatchers.IO) {
        Log.d("MEDIA_PLAYER-Load", path)
        synchronized(source) {
            if (source.source.path != path || !source.loaded) {
                source = LoadSource(Source(path = path), false)
                prepare { mp -> mp.setDataSource(path) }
            }
        }
        equalizer
    }

    override suspend fun load(descr: AssetFileDescriptor) = withContext(Dispatchers.IO) {
        synchronized(source) {
            if (source.source.descriptor != descr || !source.loaded) {
                source = LoadSource(Source(descriptor = descr), false)

                prepare { mp ->
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mp.setDataSource(descr)
                    } else mp.setDataSource(descr.fileDescriptor, descr.startOffset, descr.length)
                }
            }
        }
        equalizer
    }

    private fun prepare(load: (mp: MediaPlayer) -> Unit) {
        if (player?.isPlaying == true) player?.stop()
        player?.reset()
        with(player()) { load.invoke(this); prepare() }
    }

    override fun stop(resourceId: Int) = synchronized(source) {
        if (source.source.resourceId == resourceId && player().isPlaying) player().stop()
    }

    override fun stop(path: String) = synchronized(source) {
        if (source.source.path == path && player().isPlaying) player().stop()
    }

    override fun stop(descriptor: AssetFileDescriptor) = synchronized(source) {
        if (source.source.descriptor == descriptor && player().isPlaying) player().stop()
    }

    override fun pause(resourceId: Int) = synchronized(source) {
        if (source.source.resourceId == resourceId && player().isPlaying) player().pause()
    }

    override fun pause(path: String) = synchronized(source) {
        if (source.source.path == path && player().isPlaying) player().pause()
    }

    override fun pause(descriptor: AssetFileDescriptor) = synchronized(source) {
        if (source.source.descriptor == descriptor && player().isPlaying) player().pause()
    }

    override fun resume(resourceId: Int) = synchronized(source) {
        if (source.source.resourceId == resourceId) player().start()
    }

    override fun resume(path: String) = synchronized(source) {
        if (source.source.path == path) player().start()
    }

    override fun resume(descriptor: AssetFileDescriptor) = synchronized(source) {
        if (source.source.descriptor == descriptor) player().start()
    }

    override fun release() {
        source = LoadSource.empty
        player?.release()
        player = null
    }

    private fun player(): MediaPlayer {
        if (player == null) player = MediaPlayer().apply {
            setAudioAttributes(attrs)
            setOnPreparedListener { if (!source.loaded) source = source.copy(loaded = true) }
        }

        return player!!
    }

    private inner class SoundMediaEqualizer : Equalizer {
        private var leftVolume = 1f
        private var rightVolume = 1f
        private var repeat = 0
        private var rate = 1f

        override fun volume(left: Float, right: Float): Equalizer {
            return also { leftVolume = left; rightVolume = right }
        }

        override fun leftVolume(volume: Float) = also { leftVolume = volume }

        override fun rightVolume(volume: Float) = also { rightVolume = volume }

        override fun looping() = also { repeat = -1 }

        @RequiresApi(Build.VERSION_CODES.M)
        override fun rate(rate: Float) = also { this.rate = rate }

        override suspend fun play() {
            val mp = player()
            mp.setVolume(leftVolume, rightVolume)
            mp.isLooping = repeat == -1

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mp.playbackParams = PlaybackParams().apply { speed = rate }
            }

            return suspendCoroutine { cont ->
                mp.setOnCompletionListener { cont.resume(Unit) }
                mp.setOnErrorListener { _, what, extra ->
                    Log.d("MEDIA_PLAYER-error", "what: $what extra: $extra")
                    cont.resumeWithException(MediaPlayerError(what, extra))
                    true
                }

                mp.start()
            }
        }
    }
}