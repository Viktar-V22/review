package com.lexicon.navigation

import com.interactors.translation.NavTarget
import com.lexicon.core.navigation.Router
import com.presentation.core.Navigation
import javax.inject.Inject

class TranslationNavigation @Inject constructor(
    private val router: Router
) : Navigation<NavTarget> {

    override fun navigate(target: NavTarget) = when (target) {
        is NavTarget.Back -> router.navigateUp()
    }
}