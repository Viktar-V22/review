package com.data.voices

import com.boundaries.voices.PhraseStore
import com.core.common.PropertiesStore

class PhraseStoreImpl(private val properties: PropertiesStore) : PhraseStore {

    private companion object {
        private const val SPEECH_PHRASE = "speech_phrase"
        private const val DEF_PHRASE = "London the capital of Great Britain"
    }

    override fun store(phrase: String) = properties.putString(SPEECH_PHRASE, phrase)

    override fun phrase() = properties.getString(SPEECH_PHRASE, DEF_PHRASE)
}