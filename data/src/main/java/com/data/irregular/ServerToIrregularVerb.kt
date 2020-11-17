package com.data.irregular

import com.core.common.Mapper
import com.data.base.db.DB.Constants.INIT_ID
import com.data.base.db.irregulars.IrregularVerb
import com.data.base.db.irregulars.Verb
import com.data.base.db.irregulars.VerbType
import javax.inject.Inject

class ServerToIrregularVerb @Inject constructor() : Mapper<ServerIrregular, IrregularVerb> {

    override fun transform(entity: ServerIrregular): IrregularVerb {
        val infinitive = with(entity.infinitive) {
            Verb(INIT_ID, INIT_ID, sound, transcription, word, VerbType.INFINITIVE.toString())
        }
        val past = with(entity.past) {
            Verb(INIT_ID, INIT_ID, sound, transcription, word, VerbType.PAST.toString())
        }
        val pastParticiple = with(entity.pastParticiple) {
            Verb(INIT_ID, INIT_ID, sound, transcription, word, VerbType.PAST_PARTICIPLE.toString())
        }
        return IrregularVerb(INIT_ID, entity.ru, listOf(infinitive, past, pastParticiple))
    }
}