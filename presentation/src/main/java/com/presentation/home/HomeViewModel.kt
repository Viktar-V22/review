package com.presentation.home

import androidx.lifecycle.ViewModel
import com.core.common.TrainType
import com.interactors.home.Interactor

class HomeViewModel(
    val form: HomeForm,
    private val interactor: Interactor
) : ViewModel() {

    init {
        refreshItems()
    }

    fun menuClick(type: TrainType, menu: TrainMenu) {
        if (menu == TrainMenu.MUTE) interactor.reverseMute(type) else
            interactor.count(type, menu.toTrainCount())

        refreshItems()
    }

    private fun refreshItems() {
        form.trains.set(interactor.items())
    }
}