package com.presentation.audition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactors.audition.Interactor
import com.interactors.audition.State
import com.presentation.core.extensions.safeGet
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AuditionViewModel(
    val form: AuditionForm,
    private val interactor: Interactor
) : ViewModel() {

    private var speech: Job? = null

    init {
        refreshState()
    }

    fun speech() {
        speech?.cancel()
        speech = viewModelScope.launch { interactor.speech() }
    }

    fun clickFab() {
        viewModelScope.launch {
            interactor.checkOrNext(form.answer.safeGet())
            refreshState()
        }
    }

    private fun refreshState() {
        viewModelScope.launch {
            val state = interactor.state()
            if (state is State.Question) speech()
            form.state(state)
        }
    }
}