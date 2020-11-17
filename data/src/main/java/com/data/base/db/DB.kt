package com.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.data.base.db.DB.Constants.BD_VERSION
import com.data.base.db.images.Image
import com.data.base.db.images.ImagesDao
import com.data.base.db.irregulars.Irregular
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.base.db.irregulars.Verb
import com.data.base.db.trains.TrainsResult
import com.data.base.db.trains.TrainsResultDao
import com.data.base.db.words.Word
import com.data.base.db.words.WordsDao

@Database(
    entities = [TrainsResult::class, Word::class, Irregular::class, Verb::class, Image::class],
    version = BD_VERSION
)
@TypeConverters(ListStringConverter::class, SetStringConverter::class)
abstract class DB : RoomDatabase() {

    companion object Constants {
        const val DB_NAME = "lexicon.db"
        internal const val BD_VERSION = 1
        internal const val EXIST = 1
        internal const val INIT_ID = 0L
    }

    abstract fun trainsResultDao(): TrainsResultDao

    abstract fun wordsDao(): WordsDao

    abstract fun irregularVerbDao(): IrregularVerbsDao

    abstract fun imagesDao(): ImagesDao
}