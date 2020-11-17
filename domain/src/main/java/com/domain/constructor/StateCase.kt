package com.domain.constructor

import com.interactors.constructor.ItemLetter
import com.interactors.constructor.State

interface StateCase {

    suspend fun state(): State

    suspend fun select(item: ItemLetter): Boolean

    fun undoOrNext()

    fun en(): String

    fun isMute(): Boolean
}