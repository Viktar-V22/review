package com.lexicon.di.glossary

import com.domain.glossary.InteractorImpl
import com.domain.glossary.StateCase
import com.domain.glossary.StateCaseImpl
import com.interactors.glossary.Interactor
import com.interactors.glossary.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.navigation.GlossaryNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module

@Module
interface GlossaryModule {

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: GlossaryNavigation): Navigation<NavTarget>

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideState(state: StateCaseImpl): StateCase
}