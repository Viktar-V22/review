package com.boundaries.voices

interface PhraseStore {

    fun store(phrase: String)

    fun phrase(): String
}