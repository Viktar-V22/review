package com.domain.translation

import com.core.common.Language
import com.interactors.translation.ItemTranslation
import com.interactors.translation.State

interface StateCase {

    suspend fun sourceLang(source: Language)

    suspend fun state(): State

    suspend fun select(item: ItemTranslation)

    fun isMute(): Boolean

    fun wordSpeech(isForce: Boolean): String

    fun forSpeech(item: ItemTranslation): String

    fun next()
}