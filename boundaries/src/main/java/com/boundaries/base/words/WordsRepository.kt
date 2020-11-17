package com.boundaries.base.words

interface WordsRepository {

    suspend fun prepare()

    suspend fun words(enabled: Boolean? = true): List<Word>

    suspend fun words(ids: Set<Long>): List<Word>

    suspend fun enable(id: Long, isEnabled: Boolean)

    suspend fun enable(enabled: HashMap<Long, Boolean>)
}