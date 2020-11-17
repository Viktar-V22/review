package com.data.base.sound

import com.boundaries.base.sound.Sound
import com.data.R

fun Sound.rawRes(): Int = when (this) {
    Sound.CORRECT -> R.raw.correct
    Sound.INCORRECT -> R.raw.incorrect
    Sound.TYPEWRITER -> R.raw.typewriter
}