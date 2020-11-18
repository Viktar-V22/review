package com.lexicon.di.audition

import com.domain.audition.InteractorImpl
import com.domain.audition.StateCase
import com.domain.audition.StateCaseImpl
import com.interactors.audition.Interactor
import com.interactors.audition.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.navigation.AuditionNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module

@Module
interface AuditionModule {

    @Binds
    @FeatureScope
    fun provideStateCase(state: StateCaseImpl): StateCase

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: AuditionNavigation): Navigation<NavTarget>
}