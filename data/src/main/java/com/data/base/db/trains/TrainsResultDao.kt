package com.data.base.db.trains

import androidx.room.*
import com.data.base.db.DB.Constants.INIT_ID
import com.data.base.db.trains.TrainsResult.Companion.COUNTER
import com.data.base.db.trains.TrainsResult.Companion.ID
import com.data.base.db.trains.TrainsResult.Companion.TRAIN
import com.data.base.db.trains.TrainsResult.Companion.TRAINS_RESULT_TABLE
import com.data.base.db.trains.TrainsResult.Companion.WORD_ID

@Dao
interface TrainsResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(verb: TrainsResult): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(verbs: List<TrainsResult>)

    suspend fun increase(wordId: Long, trainType: String) = update(wordId, trainType, true)

    suspend fun decrease(wordId: Long, trainType: String) = update(wordId, trainType, false)

    @Query("select * from $TRAINS_RESULT_TABLE")
    suspend fun getAll(): List<TrainsResult>

    @Query("update $TRAINS_RESULT_TABLE set $COUNTER = :counter where $ID = :id")
    suspend fun update(id: Long, counter: Int)

    @Query("select * from $TRAINS_RESULT_TABLE where $WORD_ID = :wordId and $TRAIN = :trainType limit 1")
    suspend fun result(wordId: Long, trainType: String): List<TrainsResult>

    @Query("select * from $TRAINS_RESULT_TABLE where $TRAIN = :trainType order by $COUNTER desc limit :count")
    suspend fun minResult(trainType: String, count: Int): List<TrainsResult>

    @Transaction
    private suspend fun update(wordId: Long, trainType: String, isCorrect: Boolean) {
        val counter = if (isCorrect) 1 else -1
        val results = result(wordId, trainType)
        if (results.isEmpty()) store(TrainsResult(INIT_ID, wordId, trainType, counter)) else {
            val result = results.first()
            update(result.id, result.counter + counter)
        }
    }
}