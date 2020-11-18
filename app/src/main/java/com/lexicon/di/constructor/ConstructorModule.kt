package com.lexicon.di.constructor

import com.domain.constructor.InteractorImpl
import com.domain.constructor.StateCase
import com.domain.constructor.StateCaseImpl
import com.interactors.constructor.Interactor
import com.interactors.constructor.NavTarget
import com.lexicon.di.FeatureScope
import com.lexicon.navigation.ConstructorNavigation
import com.presentation.core.Navigation
import dagger.Binds
import dagger.Module

@Module
interface ConstructorModule {

    @Binds
    @FeatureScope
    fun provideStateCase(stateCaseImpl: StateCaseImpl): StateCase

    @Binds
    @FeatureScope
    fun provideInteractor(interactor: InteractorImpl): Interactor

    @Binds
    @FeatureScope
    fun provideNavigation(navigation: ConstructorNavigation): Navigation<NavTarget>
}