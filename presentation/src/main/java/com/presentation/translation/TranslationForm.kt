package com.presentation.translation

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.core.common.Language
import com.core.common.Result
import com.interactors.translation.ItemTranslation
import com.interactors.translation.State
import javax.inject.Inject

class TranslationForm @Inject constructor() {

    val source = ObservableField("")
    val transcription = ObservableField("")
    val sourceLang = ObservableField(Language.UNKNOWN)
    val imageUrl = ObservableField<String>()
    val result = ObservableField(Result.NONE)
    val translations = ObservableField<List<ItemTranslation>>(emptyList())
    val complete = ObservableBoolean()

    fun state(state: State) = when (state) {
        is State.Translation -> {
            source.set(state.source)
            transcription.set(state.transcription)
            imageUrl.set(state.imageUrl)
            result.set(state.result)
            translations.set(state.translations)
            sourceLang.set(state.sourceLang)
            complete.set(false)
        }
        is State.Complete -> complete.set(true)
    }
}