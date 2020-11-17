package com.presentation.home

import com.interactors.home.TrainCount
import com.presentation.R

enum class TrainMenu {
    MUTE,
    COUNT_10,
    COUNT_20,
    COUNT_30,
    ALL
}

fun Int.toTrainMenu() = when (this) {
    R.id.mute -> TrainMenu.MUTE
    R.id.count_10 -> TrainMenu.COUNT_10
    R.id.count_20 -> TrainMenu.COUNT_20
    R.id.count_30 -> TrainMenu.COUNT_30
    R.id.count_all -> TrainMenu.ALL
    else -> throw  IllegalArgumentException("unexpected id: $this")
}

fun TrainMenu.toTrainCount() = when (this) {
    TrainMenu.COUNT_10 -> TrainCount.TEN
    TrainMenu.COUNT_20 -> TrainCount.TWENTY
    TrainMenu.COUNT_30 -> TrainCount.THIRTY
    TrainMenu.ALL -> TrainCount.ALL
    else -> throw IllegalArgumentException("unexpected menu: $this")
}