package com.lexicon.di.translation

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.base.words.WordsRepository
import com.domain.translation.InteractorImpl
import com.domain.translation.StateCase
import com.domain.translation.StateCaseImpl
import com.interactors.translation.Interactor
import com.interactors.translation.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.di.base.trains.EnRuStore
import com.lexicon.di.base.trains.RuEnStore
import com.lexicon.navigation.TranslationNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface TranslationModule {

    @Module
    companion object {

        @Provides
        @FeatureScope
        fun provideStateCase(
            words: WordsRepository,
            images: ImagesRepository,
            results: TrainsResultRepository,
            @RuEnStore
            ruEnSettings: TrainsSettingsStore,
            @EnRuStore
            enRuSettings: TrainsSettingsStore
        ): StateCase = StateCaseImpl(words, images, results, ruEnSettings, enRuSettings)
    }

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: TranslationNavigation): Navigation<NavTarget>
}