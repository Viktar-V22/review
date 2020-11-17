package com.interactors.home

import com.core.common.TrainType

data class ItemTrain(
    val train: TrainType,
    val count: TrainCount,
    val isMute: Boolean
)