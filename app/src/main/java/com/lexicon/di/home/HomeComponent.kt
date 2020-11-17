package com.lexicon.di.home

import com.lexicon.di.FeatureScope
import com.lexicon.di.main.MainComponent
import com.presentation.home.HomeFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [HomeModule::class]
)
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}