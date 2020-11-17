package com.data.base.db.irregulars

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.base.db.irregulars.Irregular.Companion.IRREGULARS_TABLE

@Entity(tableName = IRREGULARS_TABLE)
data class Irregular(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = RU)
    val ru: Set<String>
) {

    companion object {
        internal const val IRREGULARS_TABLE = "irregulars"
        internal const val ID = "id"
        internal const val RU = "ru"
    }
}