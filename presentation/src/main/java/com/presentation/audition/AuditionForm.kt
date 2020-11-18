package com.presentation.audition

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.core.common.Result
import com.interactors.audition.State
import com.presentation.core.extensions.clear
import javax.inject.Inject

class AuditionForm @Inject constructor() {

    val en = ObservableField("")
    val transcription = ObservableField("")
    val ru = ObservableField("")
    val imageUrl = ObservableField<String>()
    val result = ObservableField(Result.NONE)
    val completed = ObservableBoolean()
    val answer = ObservableField("")
    val keyboard = ObservableBoolean()
    val error = ObservableField("")

    fun state(state: State) {
        when (state) {
            is State.Question -> {
                en.clear()
                ru.clear()
                answer.clear()
                error.clear()
                transcription.clear()
                result.set(Result.NONE)
                imageUrl.set(state.imageUrl)
            }
            is State.Answer -> {
                en.set(state.en)
                ru.set(state.ru)
                transcription.set(state.transcription)
                result.set(state.result)
                error.set(" ")
            }
            State.Completed -> completed.set(true)
        }
    }

    fun keyboard(showed: Boolean) {
        keyboard.set(showed)
    }
}