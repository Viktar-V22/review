package com.boundaries.base.trains

import com.core.common.TrainType

interface TrainsSettingsStore {

    fun mute(trainType: TrainType, isMute: Boolean)

    fun isMute(trainType: TrainType): Boolean

    fun count(trainType: TrainType, count: Int)

    fun count(trainType: TrainType): Int
}