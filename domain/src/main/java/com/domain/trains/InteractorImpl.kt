package com.domain.trains

import com.core.common.TrainType
import com.interactors.trains.Interactor
import com.interactors.trains.TrainCount
import javax.inject.Inject

class InteractorImpl @Inject constructor(
    private val state: StateCase
) : Interactor {

    override fun items() = state.items()

    override fun reverseMute(type: TrainType) = state.reverseMute(type)

    override fun count(type: TrainType, count: TrainCount) = state.count(type, count)
}