package com.data.base.db.words

import androidx.room.*
import com.data.base.db.words.Word.Companion.ENABLED
import com.data.base.db.words.Word.Companion.ID
import com.data.base.db.words.Word.Companion.WORDS_TABLE

@Dao
interface WordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(word: Word): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(words: List<Word>)

    @Query("select * from $WORDS_TABLE")
    suspend fun getAll(): List<Word>

    @Query("select * from $WORDS_TABLE where $ENABLED = :isEnabled")
    suspend fun getAll(isEnabled: Boolean): List<Word>

    @Query("select $ID from $WORDS_TABLE")
    suspend fun getAllIds(): List<Long>

    @Query("select * from $WORDS_TABLE where $ID = :id limit 1")
    suspend fun byId(id: Long): List<Word>

    @Query("select * from $WORDS_TABLE where $ID in (:ids)")
    suspend fun byIds(ids: List<Long>): List<Word>

    @Query("select count(*) from $WORDS_TABLE")
    suspend fun count(): Int

    @Update
    suspend fun update(word: Word)

    @Update
    suspend fun update(words: List<Word>)

    @Transaction
    suspend fun updateEnabled(id: Long, isEnabled: Boolean) {
        val words = byId(id)
        val word = if (words.isEmpty()) null else words.first()
        if (word != null && word.enabled != isEnabled) update(word.copy(enabled = isEnabled))
    }

    @Transaction
    suspend fun updateEnabled(enabled: HashMap<Long, Boolean>) {
        val update = byIds(enabled.keys.toList()).filter { it.enabled != enabled[it.id] }
            .map { it.copy(enabled = enabled[it.id]!!) }
        update(update)
    }
}