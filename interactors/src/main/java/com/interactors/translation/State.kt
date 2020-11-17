package com.interactors.translation

import com.core.common.Language
import com.core.common.Result

sealed class State {
    data class Translation(
        val source: String,
        val sourceLang: Language,
        val transcription: String,
        val translations: List<ItemTranslation>,
        val imageUrl: String,
        val result: Result,
        val speechSource: Boolean
    ) : State()

    object Complete : State()
}