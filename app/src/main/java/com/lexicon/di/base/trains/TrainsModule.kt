package com.lexicon.di.base.trains

import android.content.Context
import android.content.SharedPreferences
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.core.common.PropertiesStore
import com.data.base.db.irregulars.IrregularVerbsDao
import com.data.base.db.trains.TrainsResultDao
import com.data.base.db.words.WordsDao
import com.data.base.trains.TrainsResultRepositoryImpl
import com.data.base.trains.TrainsSettingsStoreImpl
import com.data.core.PreferenceStore
import com.lexicon.di.main.MainScope
import dagger.Module
import dagger.Provides

@Module
class TrainsModule {

    private companion object {
        private const val TRAINS_SETTINGS = "trains_settings_prefs"
    }

    @Provides
    @MainScope
    @TrainsSettings
    fun provideTrainsSettingsPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(TRAINS_SETTINGS, Context.MODE_PRIVATE)
    }

    @Provides
    @MainScope
    @TrainsSettings
    fun provideTrainsSettingsProperties(
        @TrainsSettings
        prefs: SharedPreferences
    ): PropertiesStore = PreferenceStore(prefs)

    @Provides
    @MainScope
    fun provideIrregularStore(
        @TrainsSettings
        properties: PropertiesStore
    ): TrainsSettingsStore = TrainsSettingsStoreImpl(properties)

    @Provides
    @MainScope
    fun provideResultRepository(
        irregulars: IrregularVerbsDao,
        results: TrainsResultDao,
        words: WordsDao
    ): TrainsResultRepository = TrainsResultRepositoryImpl(irregulars, results, words)
}