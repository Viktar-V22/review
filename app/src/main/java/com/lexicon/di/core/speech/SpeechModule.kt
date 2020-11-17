package com.lexicon.di.core.speech

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import com.boundaries.core.speech.SpeechRepository
import com.boundaries.core.speech.VoicesRepository
import com.boundaries.voices.Voice
import com.core.common.Mapper
import com.core.common.PropertiesStore
import com.data.core.PreferenceStore
import com.data.core.cloud.GoogleCredentialsProvider
import com.data.core.sound.SoundPlayer
import com.data.core.speech.*
import com.data.voices.VoicesRepositoryImpl
import com.domain.core.speech.SpeechCase
import com.domain.core.speech.SpeechCaseImpl
import com.google.api.gax.core.CredentialsProvider
import com.lexicon.di.core.sound.MediaPlayer
import com.lexicon.di.main.MainScope
import dagger.Binds
import dagger.Module
import dagger.Provides
import com.google.cloud.texttospeech.v1.Voice as CloudVoice

@Module
interface SpeechModule {

    @Module
    companion object {
        private const val SPEECH_PREFS = "speech_prefs"
        
        @Provides
        @MainScope
        @SpeechProperties
        fun providePreferences(context: Context): SharedPreferences {
            return context.getSharedPreferences(SPEECH_PREFS, Context.MODE_PRIVATE)
        }

        @Provides
        @MainScope
        @SpeechProperties
        fun provideProperties(
            @SpeechProperties
            prefs: SharedPreferences
        ): PropertiesStore = PreferenceStore(prefs)

        @Provides
        @MainScope
        fun provideStore(
            @SpeechProperties
            properties: PropertiesStore
        ): VoiceStore = VoiceStoreImpl(properties)

        @Provides
        @MainScope
        fun provideVoicesRepository(
            assets: AssetManager,
            speech: Speech,
            @ToVoice
            toVoice: Mapper<CloudVoice, Voice>,
            store: VoiceStore
        ): VoicesRepository = VoicesRepositoryImpl(assets, speech, toVoice, store)

        @Provides
        @MainScope
        fun provideSpeechRepository(
            speech: Speech,
            @MediaPlayer
            player: SoundPlayer,
            speechStore: SpeechStore
        ): SpeechRepository = SpeechRepositoryImpl(speech, player, speechStore)
    }

    @Binds
    @MainScope
    fun provideVoices(case: SpeechCaseImpl): SpeechCase

    @Binds
    @MainScope
    fun provideSpeech(speech: GoogleSpeech): Speech

    @Binds
    @MainScope
    fun provideCredentialsProvider(provider: GoogleCredentialsProvider): CredentialsProvider

    @Binds
    @MainScope
    fun provideSpeechStore(store: SpeechStoreImpl): SpeechStore

    @Binds
    @MainScope
    @ToVoice
    fun provideToVoice(mapper: CloudVoiceMapper): Mapper<CloudVoice, Voice>
}