package com.data.irregular

import com.boundaries.irregulars.Irregular
import com.boundaries.irregulars.Verb
import com.core.common.Mapper
import com.data.base.db.irregulars.IrregularVerb
import com.data.base.db.irregulars.VerbType
import javax.inject.Inject

class DbToIrregular @Inject constructor() : Mapper<IrregularVerb, Irregular> {

    override fun transform(entity: IrregularVerb): Irregular {
        val infType = VerbType.INFINITIVE.toString()
        val infinitive = entity.verbs.first { it.type == infType }

        val pastType = VerbType.PAST.toString()
        val past = entity.verbs.first { it.type == pastType }

        val pastParticipleType = VerbType.PAST_PARTICIPLE.toString()
        val pastParticiple = entity.verbs.first { it.type == pastParticipleType }

        return Irregular(
            entity.id,
            entity.ru,
            Verb(infinitive.en, infinitive.transcription, infinitive.sound),
            Verb(past.en, past.transcription, past.sound),
            Verb(pastParticiple.en, pastParticiple.transcription, pastParticiple.sound),
        )
    }
}