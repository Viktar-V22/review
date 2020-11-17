package com.data.core.sound.media

import com.data.core.sound.Source

data class LoadSource(
    val source: Source,
    val loaded: Boolean
) {
    internal companion object {
        val empty by lazy { LoadSource(Source(), false) }
    }
}