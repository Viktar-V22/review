package com.presentation.irregular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactors.irregular.InputField
import com.interactors.irregular.Interactor
import com.presentation.core.extensions.safeGet
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class IrregularViewModel(
    val form: IrregularForm,
    private val interactor: Interactor
) : ViewModel() {

    private var speech: Job? = null

    init {
        refreshState()
    }

    fun clickFab() {
        speech?.cancel()

        viewModelScope.launch {
            if (interactor.fab(form.infinitive.safeGet(), form.past.safeGet(), form.pp.safeGet())) {
                speech = viewModelScope.launch { interactor.speech() }
            }
            refreshState() // TODO
        }
    }

    fun speech(field: InputField) {
        speech?.cancel()
        speech = viewModelScope.launch { interactor.speech(field) }
    }

    private fun refreshState() = viewModelScope.launch { form.state(interactor.state()) }
}