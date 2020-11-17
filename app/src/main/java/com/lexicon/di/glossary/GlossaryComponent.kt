package com.lexicon.di.glossary

import com.lexicon.di.FeatureScope
import com.lexicon.di.base.images.ImagesModule
import com.lexicon.di.base.words.WordsModule
import com.lexicon.di.main.MainComponent
import com.presentation.glossary.GlossaryFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [WordsModule::class, ImagesModule::class, GlossaryModule::class]
)
interface GlossaryComponent {

    fun inject(fragment: GlossaryFragment)
}