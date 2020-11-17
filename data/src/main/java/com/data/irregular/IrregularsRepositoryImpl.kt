package com.data.irregular

import android.content.res.AssetManager
import com.boundaries.irregulars.Irregular
import com.boundaries.irregulars.IrregularsRepository
import com.core.common.Mapper
import com.data.base.db.irregulars.IrregularVerb
import com.data.base.db.irregulars.IrregularVerbsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class IrregularsRepositoryImpl(
    private val assets: AssetManager,
    private val irregulars: IrregularVerbsDao,
    private val toIrregularVerb: Mapper<ServerIrregular, IrregularVerb>,
    private val toDbIrregular: Mapper<IrregularVerb, Irregular>
) : IrregularsRepository {

    private companion object {
        private const val IRREGULARS_FILE = "irregulars.json"
    }

    override suspend fun prepare() = withContext(Dispatchers.IO) {
        if (irregulars.count() == 0) {
            val raw = assets.open(IRREGULARS_FILE).use {
                it.bufferedReader().use { buffer -> buffer.readText() }
            }
            val verbs = Json.decodeFromString<List<ServerIrregular>>(raw)
            irregulars.store(toIrregularVerb.transform(verbs))
        }
    }

    override suspend fun verbs() = withContext(Dispatchers.IO) {
        toDbIrregular.transform(irregulars.getAll())
    }
}