package com.presentation.trains

import androidx.lifecycle.ViewModel
import com.core.common.TrainType
import com.interactors.trains.Interactor

class TrainsViewModel(
    val form: TrainsForm,
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