package com.lexicon.di

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import com.data.base.db.images.ImagesDao
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.base.db.trains.TrainsResultDao
import com.data.base.db.words.WordsDao
import com.lexicon.di.base.db.DbModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidModule::class, DbModule::class])
interface AppComponent {

    // android
    fun appContext(): Context

    fun resources(): Resources

    fun assets(): AssetManager

    // db
    fun trainsResultDao(): TrainsResultDao

    fun irregularVerbDao(): IrregularVerbsDao

    fun imagesDao(): ImagesDao

    fun wordsDao(): WordsDao
}