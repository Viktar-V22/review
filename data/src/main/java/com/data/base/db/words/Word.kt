package com.data.base.db.words

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.base.db.words.Word.Companion.WORDS_TABLE

@Entity(tableName = WORDS_TABLE)
data class Word(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = RU)
    val ru: Set<String>,

    @ColumnInfo(name = EN)
    val en: String,

    @ColumnInfo(name = TRANSCRIPTION)
    val transcription: String,

    @ColumnInfo(name = ENABLED)
    val enabled: Boolean
) {

    companion object {
        internal const val WORDS_TABLE = "words"
        internal const val ID = "id"
        internal const val RU = "ru"
        internal const val EN = "en"
        internal const val TRANSCRIPTION = "transcription"
        internal const val ENABLED = "enabled"
    }
}