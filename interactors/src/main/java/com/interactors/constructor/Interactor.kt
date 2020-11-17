package com.interactors.constructor

interface Interactor {

    suspend fun state(): State

    suspend fun select(item: ItemLetter)

    fun fab()

    suspend fun speech()

    suspend fun click()
}