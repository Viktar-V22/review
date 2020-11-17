package com.data.core.sound.soundpool

import com.data.core.sound.Equalizer

interface StreamEqualizer : Equalizer {

    fun priority(priority: Int) : StreamEqualizer
}