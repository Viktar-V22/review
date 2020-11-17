package com.boundaries.base.trains

interface TrainsSettingsStore {

    fun mute(isMute: Boolean)

    fun isMute(): Boolean

    fun count(count: Int)

    fun count(): Int
}