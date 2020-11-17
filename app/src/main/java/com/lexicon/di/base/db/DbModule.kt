package com.lexicon.di.base.db

import android.content.Context
import androidx.room.Room
import com.data.base.db.DB
import com.data.base.db.DB.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDb(context: Context): DB = Room.databaseBuilder(context, DB::class.java, DB_NAME)
        .build()

    @Provides
    @Singleton
    fun provideTrainsResultDao(db: DB) = db.trainsResultDao()

    @Provides
    @Singleton
    fun provideWordsDao(db: DB) = db.wordsDao()

    @Provides
    @Singleton
    fun provideIrregularVerbsDao(db: DB) = db.irregularVerbDao()

    @Provides
    @Singleton
    fun provideImagesDao(db: DB) = db.imagesDao()
}