package com.lexicon.di.translation

import com.domain.translation.InteractorImpl
import com.domain.translation.StateCase
import com.domain.translation.StateCaseImpl
import com.interactors.translation.Interactor
import com.interactors.translation.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.navigation.TranslationNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module

@Module
interface TranslationModule {

    @Binds
    @FeatureScope
    fun provideStatecase(stateCase: StateCaseImpl): StateCase

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: TranslationNavigation): Navigation<NavTarget>
}