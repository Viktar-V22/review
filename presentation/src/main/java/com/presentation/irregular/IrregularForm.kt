package com.presentation.irregular

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.core.common.Result
import com.interactors.irregular.InputField.INFINITIVE
import com.interactors.irregular.State
import com.presentation.core.extensions.clear
import com.presentation.core.extensions.force
import com.presentation.core.extensions.safeGet
import javax.inject.Inject

class IrregularForm @Inject constructor() {

    val ru = ObservableField("")
    val imageUrl = ObservableField<String>()

    val infinitive = ObservableField("")
    val infinitiveResult = ObservableField(Result.NONE)
    val infinitiveError = ObservableField("")
    val infinitiveTranscription = ObservableField("")

    val past = ObservableField("")
    val pastResult = ObservableField(Result.NONE)
    val pastError = ObservableField("")
    val pastTranscription = ObservableField("")

    val pp = ObservableField("")
    val ppResult = ObservableField(Result.NONE)
    val ppError = ObservableField("")
    val ppTranscription = ObservableField("")

    val requestFocus = ObservableField(INFINITIVE)
    val keyboard = ObservableBoolean()

    val verified = ObservableBoolean()

    val completed = ObservableBoolean()

    fun state(state: State) = when (state) {
        is State.Irregular -> {
            if (verified.get() && state.verified != verified.get()) reset()

            ru.set(state.ru)
            imageUrl.set(state.imageUrl)

            infinitiveResult.set(state.infinitiveResult)
            pastResult.set(state.pastResult)
            ppResult.set(state.ppResult)

            infinitiveTranscription.set(if (state.infinitiveResult == Result.NONE) "" else state.infinitiveTranscription)
            pastTranscription.set(if (state.pastResult == Result.NONE) "" else state.pastTranscription)
            ppTranscription.set(if (state.ppResult == Result.NONE) "" else state.ppTranscription)

            infinitiveError.set(error(state.infinitiveResult, state.infinitive))
            pastError.set(error(state.pastResult, state.past))
            ppError.set(error(state.ppResult, state.pp))

            if (state.verified) {
                if (infinitive.safeGet().isEmpty()) infinitive.set(" ")
                if (past.safeGet().isEmpty()) past.set(" ")
                if (pp.safeGet().isEmpty()) pp.set(" ")
            }

            verified.set(state.verified)
        }
        is State.Completed -> completed.set(true)
    }

    fun keyboard(showed: Boolean) {
        keyboard.set(showed)
    }

    private fun reset() {
        infinitive.clear()
        past.clear()
        pp.clear()

        infinitiveResult.set(Result.NONE)
        pastResult.set(Result.NONE)
        ppResult.set(Result.NONE)

        infinitiveError.clear()
        pastError.clear()
        ppError.clear()

        infinitiveTranscription.clear()
        pastTranscription.clear()
        ppTranscription.clear()

        verified.set(false)
        requestFocus.force(INFINITIVE)
    }

    private fun error(result: Result, negative: String) = when (result) {
        Result.NONE -> ""
        Result.POSITIVE -> " "
        Result.NEGATIVE -> negative
    }
}