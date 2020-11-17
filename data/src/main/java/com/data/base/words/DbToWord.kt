package com.data.base.words

import com.boundaries.base.words.Word
import com.core.common.Mapper
import javax.inject.Inject
import com.data.base.db.words.Word as DbWord

class DbToWord @Inject constructor() : Mapper<DbWord, Word> {

    override fun transform(entity: DbWord): Word {
        return Word(entity.id, entity.en, entity.ru, entity.transcription, entity.enabled)
    }
}