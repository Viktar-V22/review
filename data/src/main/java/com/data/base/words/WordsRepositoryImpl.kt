package com.data.base.words

import android.content.res.AssetManager
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.core.common.Mapper
import com.data.base.db.words.WordsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.data.base.db.words.Word as DbWord

class WordsRepositoryImpl(
    private val assets: AssetManager,
    private val words: WordsDao,
    private val toDbWord: Mapper<ServerWord, DbWord>,
    private val toWord: Mapper<DbWord, Word>
) : WordsRepository {

    private companion object {
        private const val WORDS_FILE = "words.json"
    }

    override suspend fun prepare() = withContext(Dispatchers.IO) {
        if (words.count() == 0) {
            val raw = assets.open(WORDS_FILE).use {
                it.bufferedReader().use { buffer -> buffer.readText() }
            }
            words.store(toDbWord.transform(Json.decodeFromString<List<ServerWord>>(raw)))
        }
    }

    override suspend fun words(enabled: Boolean?) = withContext(Dispatchers.IO) {
        toWord.transform(if (enabled == null) words.getAll() else words.getAll(enabled))
    }

    override suspend fun words(ids: Set<Long>) = withContext(Dispatchers.IO) {
        toWord.transform(words.byIds(ids.toList()))
    }

    override suspend fun enable(id: Long, isEnabled: Boolean) = withContext(Dispatchers.IO) {
        words.updateEnabled(id, isEnabled)
    }

    override suspend fun enable(enabled: HashMap<Long, Boolean>) = withContext(Dispatchers.IO) {
        words.updateEnabled(enabled)
    }
}