package com.lexicon.di.irregular

import com.lexicon.di.FeatureScope
import com.lexicon.di.base.images.ImagesModule
import com.lexicon.di.main.MainComponent
import com.presentation.irregular.IrregularFragment
import dagger.Component

@FeatureScope
@Component(
    dependencies = [MainComponent::class],
    modules = [ImagesModule::class, IrregularModule::class]
)
interface IrregularComponent {
    fun inject(fragment: IrregularFragment)
}