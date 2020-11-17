package com.interactors.home

import com.core.common.TrainType

interface Interactor {

    fun items(): List<ItemTrain>

    fun reverseMute(type: TrainType)

    fun count(type: TrainType, count: TrainCount)
}