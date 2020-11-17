package com.interactors.constructor

import com.core.common.Result

sealed class State {

    data class Word(
        val selected: String,
        val ru: String,
        val en: String,
        val transcription: String,
        val imageUrl: String,
        val items: List<ItemLetter>,
        val result: Result
    ) : State()

    object Complete : State()
}