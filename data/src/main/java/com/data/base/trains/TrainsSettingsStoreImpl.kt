package com.data.base.trains

import com.boundaries.base.trains.TrainsSettingsStore
import com.core.common.PropertiesStore

class TrainsSettingsStoreImpl(private val properties: PropertiesStore) : TrainsSettingsStore {

    private companion object {
        private const val MUTE = "mute"
        private const val COUNT = "count"
    }

    override fun mute(isMute: Boolean) = properties.putBoolean(MUTE, isMute)

    override fun isMute() = properties.getBoolean(MUTE)

    override fun count(count: Int) = properties.putInt(COUNT, count)

    override fun count() = properties.getInt(COUNT, -1)
}