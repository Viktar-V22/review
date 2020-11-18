package com.interactors.trains

import com.core.common.Language

sealed class NavTarget {
    object ToConstructor : NavTarget()
    object ToIrregular : NavTarget()
    object ToAudition : NavTarget()
    class ToTranslation(val source: Language) : NavTarget()
}