package com.data.base.trains

import com.core.common.TrainType
import com.core.common.TrainType.*

fun TrainType.toPrefix() = when (this) {
    CONSTRUCTOR -> "constructor_"
    TRANSLATION_RU_EN -> "ru_en_"
    TRANSLATION_EN_RU -> "en_ru_"
    IRREGULAR -> "irregular_"
    AUDITION -> "audition_"
}