package com.data.core.sound.soundpool

import com.data.core.sound.Source

data class LoadSource(
    val source: Source,
    val sampleId: Int = -1,
    val streamId: Int = -1
)