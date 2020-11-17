package com.lexicon.di.main

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import com.boundaries.base.sound.SoundRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.data.base.db.images.ImagesDao
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.base.db.trains.TrainsResultDao
import com.data.base.db.words.WordsDao
import com.domain.core.speech.SpeechCase
import com.lexicon.core.navigation.Router
import com.lexicon.di.AppComponent
import com.lexicon.di.base.trains.*
import com.lexicon.di.core.sound.SoundModule
import com.lexicon.di.core.speech.SpeechModule
import com.lexicon.di.navigation.NavigationModule
import com.lexicon.main.MainActivity
import dagger.Component

@MainScope
@Component(
    modules = [NavigationModule::class, SoundModule::class, SpeechModule::class, TrainsModule::class],
    dependencies = [AppComponent::class]
)
interface MainComponent {

    // app -> android
    fun appContext(): Context

    fun resources(): Resources

    fun assets(): AssetManager

    // activity -> navigation
    fun router(): Router

    // sound
    fun soundRepository(): SoundRepository

    // speech
    fun speechCase(): SpeechCase

    // trains
    @IrregularStore
    fun irregularStore(): TrainsSettingsStore

    @RuEnStore
    fun ruEnStore(): TrainsSettingsStore

    @EnRuStore
    fun enRuStore(): TrainsSettingsStore

    @ConstructorStore
    fun constructorStore(): TrainsSettingsStore

    fun resultRepository(): TrainsResultRepository

    // db
    fun trainsResultDao(): TrainsResultDao

    fun irregularVerbDao(): IrregularVerbsDao

    fun imagesDao(): ImagesDao

    fun wordsDao(): WordsDao

    fun inject(activity: MainActivity)
}