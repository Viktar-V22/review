package com.interactors.irregular

import com.core.common.Result

sealed class State {
    data class Irregular(
        val imageUrl: String,
        val ru: String,
        val infinitive: String,
        val past: String,
        val pp: String,
        val infinitiveResult: Result,
        val pastResult: Result,
        val ppResult: Result,
        val infinitiveTranscription: String,
        val pastTranscription: String,
        val ppTranscription: String,
        val verified: Boolean
    ) : State() {

        companion object {
            fun notVerified(ru: String, imageUrl: String) = Irregular(
                imageUrl,
                ru,
                "",
                "",
                "",
                Result.NONE,
                Result.NONE,
                Result.NONE,
                "",
                "",
                "",
                false
            )
        }
    }

    object Completed : State()
}