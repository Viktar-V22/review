package com.data.base.db.irregulars

import androidx.room.ColumnInfo
import androidx.room.Relation

class IrregularVerb(

    @ColumnInfo(name = Irregular.ID)
    val id: Long,

    @ColumnInfo(name = Irregular.RU)
    val ru: Set<String>,

    @Relation(parentColumn = Irregular.ID, entityColumn = Verb.IRREGULAR_ID, entity = Verb::class)
    val verbs: List<Verb>
)