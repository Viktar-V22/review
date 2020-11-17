package com.core.common

import java.util.*

enum class Language(val code: String) {
    ENGLISH("en"),
    RUSSIAN("ru"),
    UNKNOWN("unknown");

    fun reverse(): Language = when (this) {
        ENGLISH -> RUSSIAN
        RUSSIAN -> ENGLISH
        UNKNOWN -> UNKNOWN
    }

    companion object {
        fun fromLocale(locale: Locale): Language {
            return values().firstOrNull { it.code == locale.language } ?: UNKNOWN
        }

        fun fromRaw(text: String?): Language = values()
            .firstOrNull { it.code == text || it.toString() == text } ?: UNKNOWN
    }
}