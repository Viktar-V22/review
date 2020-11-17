package com.lexicon.di.constructor

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.base.words.WordsRepository
import com.domain.constructor.InteractorImpl
import com.domain.constructor.StateCase
import com.domain.constructor.StateCaseImpl
import com.interactors.constructor.Interactor
import com.interactors.constructor.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.di.base.trains.ConstructorStore
import com.lexicon.navigation.ConstructorNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ConstructorModule {

    @Module
    companion object {
        @Provides
        @FeatureScope
        fun provideStateCase(
            words: WordsRepository,
            images: ImagesRepository,
            results: TrainsResultRepository,
            @ConstructorStore
            settings: TrainsSettingsStore
        ): StateCase = StateCaseImpl(words, images, results, settings)
    }

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: ConstructorNavigation): Navigation<NavTarget>
}