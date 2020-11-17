package com.data.core.speech

import com.boundaries.voices.Voice
import com.core.common.Gender.*
import com.core.common.Mapper
import com.google.cloud.texttospeech.v1.SsmlVoiceGender
import javax.inject.Inject
import com.google.cloud.texttospeech.v1.SsmlVoiceGender.FEMALE as W
import com.google.cloud.texttospeech.v1.SsmlVoiceGender.MALE as M
import com.google.cloud.texttospeech.v1.Voice as CloudVoice

class CloudVoiceMapper @Inject constructor() : Mapper<CloudVoice, Voice> {

    override fun transform(entities: List<CloudVoice>, param: Any): List<Voice> {
        param as Map<*, *>

        val maleNames = (param[MALE] as List<*>).sortedBy { it as String }
        val femaleNames = (param[FEMALE] as List<*>).sortedBy { it as String }
        val neutralNames = (param[NEUTRAL] as List<*>).sortedBy { it as String }

        val male = entities.filter { it.ssmlGender == M }.sortedBy { it.name }
        val female = entities.filter { it.ssmlGender == W }.sortedBy { it.name }
        val neutral = entities.filter { it.ssmlGender != M && it.ssmlGender != W }
            .sortedBy { it.name }

        return mutableListOf<Voice>().apply {
            addAll(male.mapIndexed { index, voice -> transform(voice, maleNames[index]!!) })
            addAll(female.mapIndexed { index, voice -> transform(voice, femaleNames[index]!!) })
            addAll(neutral.mapIndexed { index, voice -> transform(voice, neutralNames[index]!!) })
        }
    }

    override fun transform(entity: CloudVoice, param: Any) = Voice(
        entity.languageCodesList.first(),
        param as String,
        entity.name,
        transform(entity.ssmlGender),
        entity.ssmlGenderValue
    )

    private fun transform(gender: SsmlVoiceGender) = when (gender) {
        SsmlVoiceGender.MALE -> MALE
        SsmlVoiceGender.FEMALE -> FEMALE
        else -> NEUTRAL
    }
}