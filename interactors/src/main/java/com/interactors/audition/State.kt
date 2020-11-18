package com.interactors.audition

import com.core.common.Result

sealed class State {

    data class Question(
        val imageUrl: String
    ) : State()

    data class Answer(
        val en: String,
        val transcription: String,
        val ru: String,
        val result: Result
    ) : State()

    object Completed : State()
}