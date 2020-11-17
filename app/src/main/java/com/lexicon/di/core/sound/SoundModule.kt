package com.lexicon.di.core.sound

import android.content.Context
import android.media.AudioAttributes
import com.boundaries.base.sound.SoundRepository
import com.data.base.sound.SoundRepositoryImpl
import com.data.core.sound.SoundPlayer
import com.data.core.sound.media.SoundMediaPlayer
import com.data.core.sound.soundpool.SoundPoolPlayer
import com.lexicon.di.main.MainScope
import dagger.Module
import dagger.Provides

@Module
class SoundModule {

    @Provides
    @MainScope
    @MediaPlayer
    fun provideMedia(context: Context): SoundPlayer {
        val attrs = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()

        return SoundMediaPlayer(context, attrs)
    }

    @Provides
    @MainScope
    @PoolPlayer
    fun providePool(context: Context): SoundPlayer {
        val attrs = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .build()

        return SoundPoolPlayer(context, 5, attrs)
    }

    @Provides
    @MainScope
    fun provideSoundRepository(
        @PoolPlayer
        player: SoundPlayer
    ): SoundRepository = SoundRepositoryImpl(player)
}