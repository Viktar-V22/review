package com.presentation.trains

import com.interactors.trains.TrainCount
import com.interactors.trains.TrainCount.*
import com.presentation.R

fun TrainCount.toMenuId() = when (this) {
    TEN -> R.id.count_10
    TWENTY -> R.id.count_20
    THIRTY -> R.id.count_30
    ALL -> R.id.count_all
}

fun Int.toTrainCount() = when (this) {
    R.id.count_10 -> TEN
    R.id.count_20 -> TWENTY
    R.id.count_30 -> THIRTY
    R.id.count_all -> ALL
    else -> throw  IllegalArgumentException("unexpected id: $this")
}