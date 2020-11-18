package com.interactors.audition

interface Interactor {

    suspend fun state(): State

    suspend fun speech()

    suspend fun checkOrNext(text: String)
}