package com.lexicon.di.home

import com.boundaries.base.trains.TrainsSettingsStore
import com.domain.home.InteractorImpl
import com.domain.home.StateCase
import com.domain.home.StateCaseImpl
import com.interactors.home.Interactor
import com.interactors.home.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.di.base.trains.ConstructorStore
import com.lexicon.di.base.trains.EnRuStore
import com.lexicon.di.base.trains.IrregularStore
import com.lexicon.di.base.trains.RuEnStore
import com.lexicon.navigation.HomeNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface HomeModule {

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
    fun provideNavigation(navigation: HomeNavigation): Navigation<NavTarget>
}