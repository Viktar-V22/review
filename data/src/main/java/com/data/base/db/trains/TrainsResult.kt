package com.data.base.db.trains

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.base.db.trains.TrainsResult.Companion.TRAINS_RESULT_TABLE

@Entity(tableName = TRAINS_RESULT_TABLE)
data class TrainsResult(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = WORD_ID)
    val wordId: Long,

    @ColumnInfo(name = TRAIN)
    val train: String,

    @ColumnInfo(name = COUNTER)
    val counter: Int
) {

    companion object {
        internal const val TRAINS_RESULT_TABLE = "trains_result"
        internal const val ID = "id"
        internal const val WORD_ID = "word_id"
        internal const val TRAIN = "train"
        internal const val COUNTER = "counter"
    }
}