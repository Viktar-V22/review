package com.data.core.speech

import com.boundaries.voices.Voice
import com.core.common.Gender
import com.core.common.PropertiesStore

class VoiceStoreImpl(private val properties: PropertiesStore) : VoiceStore {

    private companion object {
        private const val ID = "voice_id"
        private const val NAME = "voice_name"
        private const val RAW_NAME = "voice_raw_name"
        private const val GENDER = "voice_gender"
        private const val RAW_GENDER = "voice_raw_gender"
    }

    override fun store(voice: Voice) {
        properties.putString(ID, voice.code)
        properties.putString(NAME, voice.name)
        properties.putString(RAW_NAME, voice.rawName)
        properties.putInt(GENDER, voice.gender.ordinal)
        properties.putInt(RAW_GENDER, voice.rawGender)
    }

    override fun voice(): Voice? {
        val id = properties.getString(ID)

        return if (id.isEmpty()) null else {
            val name = properties.getString(NAME)
            val rawName = properties.getString(RAW_NAME)
            val gender = properties.getInt(GENDER, Gender.NEUTRAL.ordinal)
            val rawGender = properties.getInt(RAW_GENDER)

            Voice(id, name, rawName, Gender.values()[gender], rawGender)
        }
    }
}