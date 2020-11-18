package com.lexicon.di.audition

import com.lexicon.di.FeatureScope
import com.lexicon.di.base.images.ImagesModule
import com.lexicon.di.base.words.WordsModule
import com.lexicon.di.main.MainComponent
import com.presentation.audition.AuditionFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [WordsModule::class, ImagesModule::class, AuditionModule::class]
)
interface AuditionComponent {

    fun inject(fragment: AuditionFragment)
}