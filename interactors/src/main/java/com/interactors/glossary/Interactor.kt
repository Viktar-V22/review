package com.interactors.glossary

interface Interactor {

    suspend fun items(): List<ItemWord>

    suspend fun speech(item: ItemWord)

    suspend fun reverseCheck(item: ItemWord)

    fun reverseCheck()
}