package com.presentation.constructor

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.core.common.Result
import com.interactors.constructor.ItemLetter
import com.interactors.constructor.State
import java.util.Collections.emptyList
import javax.inject.Inject

class ConstructorForm @Inject constructor() {

    val letters = ObservableField(emptyList<ItemLetter>())
    val columns = ObservableField<Int>()
    val imageUrl = ObservableField<String>()
    val ru = ObservableField("")
    val en = ObservableField("")
    val transcription = ObservableField<String>()
    val selected = ObservableField("")
    val result = ObservableField(Result.NONE)
    val complete = ObservableBoolean()

    fun state(state: State) = when (state) {
        is State.Word -> {
            letters.set(state.items)
            imageUrl.set(state.imageUrl)
            ru.set(state.ru)
            en.set(state.en)
            transcription.set(state.transcription)
            selected.set(state.selected)
            result.set(state.result)
        }
        is State.Complete -> complete.set(true)
    }
}