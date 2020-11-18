package com.domain.trains

import com.boundaries.base.trains.TrainsSettingsStore
import com.core.common.TrainType
import com.interactors.trains.ItemTrain
import com.interactors.trains.TrainCount
import javax.inject.Inject

class StateCaseImpl @Inject constructor(private val settings: TrainsSettingsStore) : StateCase {

    override fun items() = TrainType.values().map {
        ItemTrain(it, TrainCount.valueOf(settings.count(it)), settings.isMute(it))
    }

    override fun reverseMute(type: TrainType) = settings.mute(type, !settings.isMute(type))

    override fun count(type: TrainType, count: TrainCount) = settings.count(type, count.count)
}