package com.presentation.core.extensions

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow

@FlowPreview
@ExperimentalCoroutinesApi
fun <T> BroadcastChannel<T>.safeClicks(duration: Long = 300) = asFlow().throttleFirst(duration)