package com.presentation.constructor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interactors.constructor.Interactor
import com.interactors.constructor.ItemLetter
import kotlinx.coroutines.launch

class ConstructorViewModel(
    val form: ConstructorForm,
    private val interactor: Interactor
) : ViewModel() {

    init {
        refreshState()
    }

    fun select(item: ItemLetter) {
        viewModelScope.launch { interactor.click() }
        viewModelScope.launch { interactor.select(item) }
        refreshState()
    }

    fun clickFab() {
        interactor.fab()
        refreshState()
    }

    fun speech() = viewModelScope.launch { interactor.speech() }

    private fun refreshState() = viewModelScope.launch { form.state(interactor.state()) }
}