package com.presentation.home

import com.core.common.TrainType
import com.core.common.TrainType.*
import com.presentation.R

fun TrainType.toTextRes() = when (this) {
    CONSTRUCTOR -> R.string.constructor
    TRANSLATION_EN_RU -> R.string.en_ru
    TRANSLATION_RU_EN -> R.string.ru_en
    IRREGULAR -> R.string.irregular_verbs
}

fun TrainType.toIconRes() = when (this) {
    CONSTRUCTOR -> R.drawable.ic_constructor
    TRANSLATION_EN_RU, TRANSLATION_RU_EN -> R.drawable.ic_translation
    IRREGULAR -> R.drawable.ic_irregular
}