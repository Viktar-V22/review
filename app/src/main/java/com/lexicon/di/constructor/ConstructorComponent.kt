package com.lexicon.di.constructor

import com.lexicon.di.FeatureScope
import com.lexicon.di.base.images.ImagesModule
import com.lexicon.di.base.words.WordsModule
import com.lexicon.di.main.MainComponent
import com.presentation.constructor.ConstructorFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [WordsModule::class, ImagesModule::class, ConstructorModule::class]
)
interface ConstructorComponent {

    fun inject(fragment: ConstructorFragment)
}