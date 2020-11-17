package com.data.base.words

import com.core.common.Mapper
import com.data.base.db.DB.Constants.INIT_ID
import com.data.base.db.words.Word
import javax.inject.Inject

class ServerToDbWord @Inject constructor() : Mapper<ServerWord, Word> {

    override fun transform(entity: ServerWord) = Word(
        INIT_ID,
        entity.ru,
        entity.en,
        entity.transcription,
        true
    )
}