package com.domain.irregular

import com.domain.core.speech.SpeechCase
import com.interactors.irregular.InputField
import com.interactors.irregular.InputField.*
import com.interactors.irregular.Interactor
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase,
    private val speech: SpeechCase
) : Interactor {

    override suspend fun state() = state.state()

    override suspend fun fab(infinitive: String, past: String, pp: String): Boolean {
        return state.checkOrNext(infinitive, past, pp)
    }

    override suspend fun speech(field: InputField) = speech.speech(state.forSpeech(field))

    override suspend fun speech() {
        if (!state.isMute()) {
            speech(INFINITIVE)
            speech(PAST)
            speech(PAST_PARTICIPLE)
        }
    }
}