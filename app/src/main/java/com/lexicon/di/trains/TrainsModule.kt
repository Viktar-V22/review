package com.lexicon.di.trains

import com.boundaries.base.trains.TrainsSettingsStore
import com.domain.trains.InteractorImpl
import com.domain.trains.StateCase
import com.domain.trains.StateCaseImpl
import com.interactors.trains.Interactor
import com.interactors.trains.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.di.base.trains.ConstructorStore
import com.lexicon.di.base.trains.EnRuStore
import com.lexicon.di.base.trains.IrregularStore
import com.lexicon.di.base.trains.RuEnStore
import com.lexicon.navigation.TrainsNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface TrainsModule {

    @Module
    companion object {

        @Provides
        @FeatureScope
        fun provideStateCase(
            @IrregularStore
            irregular: TrainsSettingsStore,
            @RuEnStore
            ruEn: TrainsSettingsStore,
            @EnRuStore
            enRu: TrainsSettingsStore,
            @ConstructorStore
            constructor: TrainsSettingsStore
        ): StateCase = StateCaseImpl(irregular, ruEn, enRu, constructor)
    }

    @Binds
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    fun provideNavigation(navigation: TrainsNavigation): Navigation<NavTarget>
}