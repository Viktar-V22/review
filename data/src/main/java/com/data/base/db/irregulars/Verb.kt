package com.data.base.db.irregulars

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.base.db.irregulars.Verb.Companion.VERBS_TABLE

@Entity(tableName = VERBS_TABLE)
data class Verb(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = IRREGULAR_ID)
    val irregularId: Long,

    @ColumnInfo(name = SOUND)
    val sound: String,

    @ColumnInfo(name = TRANSCRIPTION)
    val transcription: String,

    @ColumnInfo(name = EN)
    val en: String,

    @ColumnInfo(name = TYPE)
    val type: String
) {

    companion object {
        internal const val VERBS_TABLE = "verbs"
        internal const val ID = "id"
        internal const val IRREGULAR_ID = "irregular_id"
        internal const val SOUND = "sound"
        internal const val TRANSCRIPTION = "transcription"
        internal const val EN = "en"
        internal const val TYPE = "type"
    }
}