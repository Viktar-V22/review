package com.interactors.irregular

interface Interactor {

    suspend fun state(): State

    suspend fun fab(infinitive: String, past: String, pp: String): Boolean

    suspend fun speech(field: InputField)

    suspend fun speech()
}