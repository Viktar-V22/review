package com.domain.translation

import com.core.common.Language
import com.domain.core.speech.SpeechCase
import com.interactors.translation.Interactor
import com.interactors.translation.ItemTranslation
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase,
    private val speech: SpeechCase
) : Interactor {

    override suspend fun prepare(source: Language) = state.sourceLang(source)

    override suspend fun state() = state.state()

    override suspend fun select(item: ItemTranslation) = state.select(item)

    override suspend fun speech(item: ItemTranslation) = speech.speech(state.forSpeech(item))

    override suspend fun speech(isForce: Boolean) {
        if (!state.isMute() || isForce) speech.speech(state.wordSpeech(isForce))
    }

    override fun fab() = state.next()
}