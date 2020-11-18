package com.domain.translation

import com.core.common.Language
import com.core.common.TrainType

fun Language.toTrainType() = when (this) {
    Language.RUSSIAN -> TrainType.TRANSLATION_RU_EN
    Language.ENGLISH -> TrainType.TRANSLATION_EN_RU
    else -> throw  IllegalArgumentException("unexpected lang: $this")
}