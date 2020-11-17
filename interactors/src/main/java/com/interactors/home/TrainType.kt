package com.interactors.home

import com.core.common.Language
import com.core.common.TrainType

fun TrainType.toNavTarget() = when (this) {
    TrainType.CONSTRUCTOR -> NavTarget.ToConstructor
    TrainType.IRREGULAR -> NavTarget.ToIrregular
    TrainType.TRANSLATION_RU_EN -> NavTarget.ToTranslation(Language.RUSSIAN)
    TrainType.TRANSLATION_EN_RU -> NavTarget.ToTranslation(Language.ENGLISH)
}