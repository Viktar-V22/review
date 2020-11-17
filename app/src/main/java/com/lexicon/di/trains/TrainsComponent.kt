package com.lexicon.di.trains

import com.lexicon.di.FeatureScope
import com.lexicon.di.main.MainComponent
import com.presentation.trains.TrainsFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [TrainsModule::class]
)
interface TrainsComponent {
    fun inject(fragment: TrainsFragment)
}