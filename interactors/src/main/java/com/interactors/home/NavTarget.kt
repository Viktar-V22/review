package com.interactors.home

import com.core.common.Language

sealed class NavTarget {
    object ToConstructor : NavTarget()
    object ToIrregular : NavTarget()
    class ToTranslation(val source: Language) : NavTarget()
}