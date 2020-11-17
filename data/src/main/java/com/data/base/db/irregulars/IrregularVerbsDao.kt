package com.data.base.db.irregulars

import androidx.room.*
import com.data.base.db.DB.Constants.INIT_ID
import com.data.base.db.irregulars.Irregular.Companion.ID
import com.data.base.db.irregulars.Irregular.Companion.IRREGULARS_TABLE

@Dao
interface IrregularVerbsDao {

    @Transaction
    fun store(irregulars: List<IrregularVerb>) {
        irregulars.forEach {
            val irregularId = store(Irregular(INIT_ID, it.ru))
            storeVerbs(it.verbs.map { verb -> verb.copy(irregularId = irregularId) })
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun store(irregular: Irregular): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeIrregulars(irregulars: List<Irregular>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun store(verb: Verb): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun storeVerbs(verbs: List<Verb>)

    @Query("select count(*) from $IRREGULARS_TABLE")
    suspend fun count(): Int

    @Transaction
    @Query("select * from $IRREGULARS_TABLE")
    suspend fun getAll(): List<IrregularVerb>

    @Query("select $ID from $IRREGULARS_TABLE")
    suspend fun getAllIds(): List<Long>
}