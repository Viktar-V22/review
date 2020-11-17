package com.interactors.glossary

data class ItemWord(
    val id: Long,
    val imageUrl: String,
    val en: String,
    val ru: String,
    val transcription: String,
    val isChecked: Boolean?
)