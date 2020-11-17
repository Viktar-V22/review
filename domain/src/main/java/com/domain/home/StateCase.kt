package com.domain.home

import com.core.common.TrainType
import com.interactors.home.ItemTrain
import com.interactors.home.TrainCount

interface StateCase {

    fun items(): List<ItemTrain>

    fun reverseMute(type: TrainType)

    fun count(type: TrainType, count: TrainCount)
}