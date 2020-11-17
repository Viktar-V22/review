package com.domain.trains

import com.core.common.TrainType
import com.interactors.trains.ItemTrain
import com.interactors.trains.TrainCount

interface StateCase {

    fun items(): List<ItemTrain>

    fun reverseMute(type: TrainType)

    fun count(type: TrainType, count: TrainCount)
}