package com.data.base.trains

import com.boundaries.base.trains.TrainsResultRepository
import com.core.common.Result
import com.core.common.TrainType
import com.core.common.TrainType.*
import com.core.common.isPositive
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.base.db.trains.TrainsResultDao
import com.data.base.db.words.WordsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainsResultRepositoryImpl @Inject constructor(
    private val irregulars: IrregularVerbsDao,
    private val results: TrainsResultDao,
    private val words: WordsDao
) : TrainsResultRepository {

    override suspend fun minResult(trainType: TrainType, count: Int) = withContext(Dispatchers.IO) {
        val all = allIds(trainType).toSet()
        val realCount = if (count != -1) all.size.coerceAtMost(count) else count

        if (count == -1) all else {
            val min = results.getAll()
            val minIds = min.map { it.id }

            when {
                all.size - min.size >= realCount -> all.filter { !minIds.contains(it) }
                    .subList(0, realCount)
                    .toSet()

                all.size - min.size == 0 -> min.sortedByDescending { it.counter }
                    .subList(0, realCount)
                    .map { it.wordId }
                    .toSet()

                else -> {
                    val raw = all.filter { !minIds.contains(it) }

                    val prev = min.sortedByDescending { it.counter }
                        .subList(0, realCount - raw.size)
                        .map { it.wordId }

                    HashSet<Long>(raw).apply { addAll(prev) }
                }
            }
        }
    }

    override suspend fun store(id: Long, trainType: TrainType, result: Result) =
        withContext(Dispatchers.IO) {
            val type = trainType.toString()
            if (result.isPositive()) results.increase(id, type) else results.decrease(id, type)
        }

    private suspend fun allIds(type: TrainType) = when (type) {
        IRREGULAR -> irregulars.getAllIds()
        TRANSLATION_EN_RU, TRANSLATION_RU_EN, CONSTRUCTOR -> words.getAllIds()
    }
}