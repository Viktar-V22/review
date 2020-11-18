package com.data.base.trains

import com.boundaries.base.trains.TrainsSettingsStore
import com.core.common.PropertiesStore
import com.core.common.TrainType

class TrainsSettingsStoreImpl(
    private val properties: PropertiesStore
) : TrainsSettingsStore {

    private companion object {
        private const val MUTE = "mute"
        private const val COUNT = "count"
    }

    override fun mute(trainType: TrainType, isMute: Boolean) {
        properties.putBoolean(trainType.toPrefix() + MUTE, isMute)
    }

    override fun isMute(trainType: TrainType): Boolean {
        return properties.getBoolean(trainType.toPrefix() + MUTE)
    }

    override fun count(trainType: TrainType, count: Int) {
        properties.putInt(trainType.toPrefix() + COUNT, count)
    }

    override fun count(trainType: TrainType): Int {
        return properties.getInt(trainType.toPrefix() + COUNT, -1)
    }
}