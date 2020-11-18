package com.lexicon.di.main

import androidx.fragment.app.Fragment
import com.lexicon.di.audition.DaggerAuditionComponent
import com.lexicon.di.constructor.DaggerConstructorComponent
import com.lexicon.di.glossary.DaggerGlossaryComponent
import com.lexicon.di.irregular.DaggerIrregularComponent
import com.lexicon.di.trains.DaggerTrainsComponent
import com.lexicon.di.translation.DaggerTranslationComponent
import com.presentation.audition.AuditionFragment
import com.presentation.constructor.ConstructorFragment
import com.presentation.glossary.GlossaryFragment
import com.presentation.irregular.IrregularFragment
import com.presentation.trains.TrainsFragment
import com.presentation.translation.TranslationFragment

class MainInjector {

    fun inject(fragment: Fragment, mainComponent: MainComponent) {
        when (fragment) {
            is ConstructorFragment -> DaggerConstructorComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)

            is TranslationFragment -> DaggerTranslationComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)

            is IrregularFragment -> DaggerIrregularComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)

            is AuditionFragment -> DaggerAuditionComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)

            is TrainsFragment -> DaggerTrainsComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)

            is GlossaryFragment -> DaggerGlossaryComponent.builder()
                .mainComponent(mainComponent)
                .build()
                .inject(fragment)
        }
    }
}