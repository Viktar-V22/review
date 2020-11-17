package com.data.core.sound

import android.content.res.AssetFileDescriptor
import androidx.annotation.RawRes

interface SoundPlayer {

    suspend fun load(@RawRes resourceId: Int): Equalizer

    suspend fun load(path: String): Equalizer

    suspend fun load(descr: AssetFileDescriptor): Equalizer

    fun stop(@RawRes resourceId: Int)

    fun stop(path: String)

    fun stop(descriptor: AssetFileDescriptor)

    fun pause(@RawRes resourceId: Int)

    fun pause(path: String)

    fun pause(descriptor: AssetFileDescriptor)

    fun resume(@RawRes resourceId: Int)

    fun resume(path: String)

    fun resume(descriptor: AssetFileDescriptor)

    fun release()
}