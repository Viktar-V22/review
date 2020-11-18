package com.lexicon.di.trains

import com.domain.trains.InteractorImpl
import com.domain.trains.StateCase
import com.domain.trains.StateCaseImpl
import com.interactors.trains.Interactor
import com.interactors.trains.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.navigation.TrainsNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module

@Module
interface TrainsModule {

    @Binds
    @FeatureScope
    fun provideStateCase(case: StateCaseImpl): StateCase

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: TrainsNavigation): Navigation<NavTarget>
}