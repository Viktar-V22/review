package com.presentation.glossary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactors.glossary.Interactor
import com.interactors.glossary.ItemWord
import com.presentation.core.extensions.reverse
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GlossaryViewModel(
    val form: GlossaryForm,
    private val interactor: Interactor
) : ViewModel() {

    private var speech: Job? = null

    fun refreshItems() {
        viewModelScope.launch { form.items.set(interactor.items()) }
    }

    fun check(item: ItemWord) {
        viewModelScope.launch { interactor.reverseCheck(item) }
    }

    fun speech(item: ItemWord) {
        speech?.cancel()
        speech = viewModelScope.launch { interactor.speech(item) }
    }

    fun fab() {
        interactor.reverseCheck()
        form.speech.reverse()
        refreshItems()
    }
}