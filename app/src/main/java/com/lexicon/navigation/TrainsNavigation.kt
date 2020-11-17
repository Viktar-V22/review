package com.lexicon.navigation

import android.os.Bundle
import com.interactors.trains.NavTarget
import com.interactors.trains.NavTarget.*
import com.lexicon.R
import com.lexicon.core.navigation.Router
import com.presentation.core.Navigation
import com.presentation.translation.TranslationFragment
import javax.inject.Inject

class TrainsNavigation @Inject constructor(private val router: Router) : Navigation<NavTarget> {

    override fun navigate(target: NavTarget) = when (target) {
        is ToTranslation -> {
            val params = Bundle()
                .apply { putString(TranslationFragment.SOURCE, target.source.code) }
            router.navigate(R.id.to_translation, params)
        }
        ToConstructor -> router.navigate(R.id.to_constructor)
        ToIrregular -> router.navigate(R.id.to_irregular)
    }
}