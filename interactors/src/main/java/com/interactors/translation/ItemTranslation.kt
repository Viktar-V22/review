package com.interactors.translation

import com.core.common.Language
import com.core.common.Result

data class ItemTranslation(
    val translation: String,
    val transcription: String,
    val sourceLang: Language,
    val correct: String,
    val result: Result = Result.NONE
)