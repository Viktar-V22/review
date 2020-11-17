package com.presentation.translation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.Language
import com.core.common.Result
import com.interactors.translation.Interactor
import com.interactors.translation.ItemTranslation
import com.interactors.translation.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TranslationViewModel(
    val form: TranslationForm,
    private val interactor: Interactor
) : ViewModel() {

    private var speech: Job? = null

    fun prepare(source: Language) {
        viewModelScope.launch { interactor.prepare(source); refreshState() }
    }

    fun clickFab() {
        speech?.cancel()
        interactor.fab()
        refreshState()
    }

    fun select(item: ItemTranslation) {
        if (form.result.get() == Result.NONE) {
            viewModelScope.launch {
                interactor.select(item)
                speech(false)
                refreshState() // TODO
            }
        }
    }

    fun speech(item: ItemTranslation) {
        speech?.cancel()
        speech = viewModelScope.launch { interactor.speech(item) }
    }

    fun speech(isForce: Boolean = true) {
        speech?.cancel()
        speech = viewModelScope.launch {
            if (isForce) interactor.forceSpeech() else interactor.speech()
        }
    }

    private fun refreshState() {
        viewModelScope.launch {
            val state = interactor.state()
            form.state(state)
            if (state is State.Translation && state.speechSource) speech(false)
        }
    }
}