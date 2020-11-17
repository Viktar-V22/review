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
        private const val IRREGULAR_PREFS = "irregular_prefs"
        private const val CONSTRUCTOR_PREFS = "constructor_prefs"
        private const val RU_EN_PREFS = "ru_en_prefs"
        private const val EN_RU_PREFS = "en_ru_prefs"
    }

    @Provides
    @MainScope
    @IrregularStore
    fun provideIrregularPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(IRREGULAR_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @MainScope
    @IrregularStore
    fun provideIrregularProperties(
        @IrregularStore
        prefs: SharedPreferences
    ): PropertiesStore = PreferenceStore(prefs)

    @Provides
    @MainScope
    @RuEnStore
    fun provideRuEnPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(RU_EN_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @MainScope
    @RuEnStore
    fun provideRuEnProperties(
        @RuEnStore
        prefs: SharedPreferences
    ): PropertiesStore = PreferenceStore(prefs)

    @Provides
    @MainScope
    @EnRuStore
    fun provideEnRuPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(EN_RU_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @MainScope
    @EnRuStore
    fun provideEnRuProperties(
        @EnRuStore
        prefs: SharedPreferences
    ): PropertiesStore = PreferenceStore(prefs)

    @Provides
    @MainScope
    @ConstructorStore
    fun provideConstructorPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(CONSTRUCTOR_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @MainScope
    @ConstructorStore
    fun provideConstructorProperties(
        @EnRuStore
        prefs: SharedPreferences
    ): PropertiesStore = PreferenceStore(prefs)

    @Provides
    @MainScope
    @IrregularStore
    fun provideIrregularStore(
        @IrregularStore
        properties: PropertiesStore
    ): TrainsSettingsStore = TrainsSettingsStoreImpl(properties)

    @Provides
    @MainScope
    @EnRuStore
    fun provideEnRuStore(
        @EnRuStore
        properties: PropertiesStore
    ): TrainsSettingsStore = TrainsSettingsStoreImpl(properties)

    @Provides
    @MainScope
    @RuEnStore
    fun provideRuEnStore(
        @RuEnStore
        properties: PropertiesStore
    ): TrainsSettingsStore = TrainsSettingsStoreImpl(properties)

    @Provides
    @MainScope
    @ConstructorStore
    fun provideConstructorStore(
        @ConstructorStore
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