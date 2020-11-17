package com.domain.irregular

import com.interactors.irregular.InputField
import com.interactors.irregular.State

interface StateCase {

    suspend fun state(): State

    fun isMute(): Boolean

    suspend fun checkOrNext(infinitive: String, past: String, pp: String): Boolean

    fun forSpeech(field: InputField): String
}