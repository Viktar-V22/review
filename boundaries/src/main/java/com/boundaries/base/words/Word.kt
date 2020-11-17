package com.boundaries.base.words

data class Word(
    val id: Long,
    val en: String,
    val ru: Set<String>,
    val transcription: String,
    val enabled: Boolean
)