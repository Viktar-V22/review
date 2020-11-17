package com.data.core.sound.media

import android.media.MediaPlayer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun MediaPlayer.onComplete() = callbackFlow {
    val listener = MediaPlayer.OnCompletionListener { offer(Unit) }
    setOnCompletionListener(listener)
    awaitClose { setOnCompletionListener(null) }
}.conflate()

@ExperimentalCoroutinesApi
fun MediaPlayer.onPrepared() = callbackFlow {
    val listener = MediaPlayer.OnPreparedListener { offer(Unit) }
    setOnPreparedListener(listener)
    awaitClose { setOnPreparedListener(null) }
}.conflate()

@ExperimentalCoroutinesApi
fun MediaPlayer.onError(processed: (Int, Int) -> Boolean = MediaErrorProcessed) = callbackFlow {
    val listener = MediaPlayer.OnErrorListener { _, what, extra ->
        offer(MediaError(what, extra))
        processed(what, extra)
    }
    setOnErrorListener(listener)
    awaitClose { setOnErrorListener(null) }
}.conflate()