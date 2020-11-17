package com.data.core.sound.soundpool

import com.data.core.sound.Equalizer

interface RepeatEqualizer : Equalizer {

    fun repeat(count: Int): RepeatEqualizer

    fun endless(): RepeatEqualizer
}