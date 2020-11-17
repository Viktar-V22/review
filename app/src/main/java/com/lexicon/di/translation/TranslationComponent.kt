package com.lexicon.di.translation

import com.lexicon.di.FeatureScope
import com.lexicon.di.base.images.ImagesModule
import com.lexicon.di.base.words.WordsModule
import com.lexicon.di.main.MainComponent
import com.presentation.translation.TranslationFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [WordsModule::class, ImagesModule::class, TranslationModule::class]
)
interface TranslationComponent {

    fun inject(fragment: TranslationFragment)
}