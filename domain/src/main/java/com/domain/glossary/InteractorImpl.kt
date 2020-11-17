package com.domain.glossary

import com.domain.core.speech.SpeechCase
import com.interactors.glossary.Interactor
import com.interactors.glossary.ItemWord
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase,
    private val speechCase: SpeechCase
) : Interactor {

    override suspend fun items() = state.items()

    override suspend fun speech(item: ItemWord) = speechCase.speech(item.en)

    override suspend fun reverseCheck(item: ItemWord) = state.reverseCheck(item)

    override fun reverseCheck() = state.reverseCheck()
}