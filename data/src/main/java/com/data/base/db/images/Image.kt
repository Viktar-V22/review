package com.data.base.db.images

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.data.base.db.images.Image.Companion.IMAGES_TABLE

@Entity(tableName = IMAGES_TABLE)
data class Image(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = RU)
    val ru: Set<String>,

    @ColumnInfo(name = URL)
    val url: String
) {

    companion object {
        internal const val IMAGES_TABLE = "images"
        internal const val ID = "id"
        internal const val RU = "ru"
        internal const val URL = "url"
    }
}