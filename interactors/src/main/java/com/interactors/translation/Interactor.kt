package com.interactors.translation

import com.core.common.Language

interface Interactor {

    suspend fun prepare(source: Language)

    suspend fun state(): State

    suspend fun select(item: ItemTranslation)

    suspend fun speech(item: ItemTranslation)

    suspend fun speech(isForce: Boolean = false)

    fun fab()
}