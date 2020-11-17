package com.domain.glossary

import com.interactors.glossary.ItemWord

interface StateCase {

    suspend fun items(): List<ItemWord>

    suspend fun reverseCheck(item: ItemWord)

    fun reverseCheck()
}