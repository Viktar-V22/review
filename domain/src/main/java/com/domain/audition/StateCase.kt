package com.domain.audition

import com.interactors.audition.State

interface StateCase {

    suspend fun state(): State

    fun forSpeech(): String

    suspend fun checkOrNext(text: String)
}