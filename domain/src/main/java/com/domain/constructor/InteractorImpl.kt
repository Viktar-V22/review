package com.domain.constructor

import com.boundaries.base.sound.Sound
import com.boundaries.base.sound.SoundRepository
import com.domain.core.speech.SpeechCase
import com.interactors.constructor.Interactor
import com.interactors.constructor.ItemLetter
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase,
    private val speech: SpeechCase,
    private val sound: SoundRepository
) : Interactor {

    override suspend fun state() = state.state()

    override suspend fun select(item: ItemLetter) {
        if (state.select(item) && !state.isMute()) speech()
    }

    override fun fab() = state.undoOrNext()

    override suspend fun speech() = speech.speech(state.en())

    override suspend fun click() {
        if (!state.isMute()) sound.play(Sound.TYPEWRITER)
    }
}