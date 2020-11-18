package com.domain.audition

import com.domain.core.speech.SpeechCase
import com.interactors.audition.Interactor
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase,
    private val speech: SpeechCase
) : Interactor {

    override suspend fun state() = state.state()

    override suspend fun speech() = speech.speech(state.forSpeech())

    override suspend fun checkOrNext(text: String) = state.checkOrNext(text)
}