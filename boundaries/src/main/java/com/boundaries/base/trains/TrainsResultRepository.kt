package com.boundaries.base.trains

import com.core.common.Result
import com.core.common.TrainType

interface TrainsResultRepository {

    suspend fun minResult(trainType: TrainType, count: Int): Set<Long>

    suspend fun store(id: Long, trainType: TrainType, result: Result)
}