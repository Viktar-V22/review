package com.domain.trains

import com.boundaries.base.trains.TrainsSettingsStore
import com.core.common.TrainType
import com.core.common.TrainType.*
import com.interactors.trains.ItemTrain
import com.interactors.trains.TrainCount

class StateCaseImpl(
    private val irregular: TrainsSettingsStore,
    private val ruEn: TrainsSettingsStore,
    private val enRu: TrainsSettingsStore,
    private val constructor: TrainsSettingsStore
) : StateCase {

    override fun items() = TrainType.values().map {
        val store = trainsStore(it)
        ItemTrain(it, TrainCount.valueOf(store.count()), store.isMute())
    }

    override fun reverseMute(type: TrainType) = with(trainsStore(type)) { mute(!isMute()) }

    override fun count(type: TrainType, count: TrainCount) = with(trainsStore(type)) {
        count(count.count)
    }

    private fun trainsStore(type: TrainType) = when (type) {
        IRREGULAR -> irregular
        TRANSLATION_EN_RU -> enRu
        TRANSLATION_RU_EN -> ruEn
        CONSTRUCTOR -> constructor
    }
}