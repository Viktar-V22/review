package com.domain.translation

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.core.common.Language
import com.core.common.Language.*
import com.core.common.Result
import com.core.common.Result.NONE
import com.core.common.Result.POSITIVE
import com.core.common.TrainType
import com.core.common.toBoolean
import com.interactors.translation.ItemTranslation
import com.interactors.translation.State
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class StateCaseImpl @Inject constructor(
    private val words: WordsRepository,
    private val images: ImagesRepository,
    private val results: TrainsResultRepository,
    private val settings: TrainsSettingsStore,
) : StateCase {

    private companion object {
        private const val TRANSLATIONS = 6
    }

    private val ruEn = Hashtable<String, Set<String>>()
    private val enRu = Hashtable<String, Set<String>>()
    private val transcriptions = Hashtable<String, String>()
    private var sourceLang = UNKNOWN

    private val trains = ArrayDeque<Word>()
    private val variants = ArrayList<String>(TRANSLATIONS)

    private var answer = ""

    override suspend fun sourceLang(source: Language) {
        if (sourceLang == UNKNOWN) prepare(source)
    }

    override suspend fun state() = if (trains.isEmpty()) State.Complete else {
        val result = result(sourceLang, answer)
        State.Translation(
            source(sourceLang).joinToString(", "),
            sourceLang,
            transcription(trains.first.en),
            toItems(variants),
            imageUrl(sourceLang, answer),
            result,
            sourceLang == ENGLISH && result == NONE
        )
    }

    override suspend fun select(item: ItemTranslation) {
        results.store(trains.first.id, trainType(sourceLang), result(sourceLang, item.translation))
        answer = item.translation
    }

    override fun isMute() = settings.isMute(sourceLang.toTrainType())

    override fun wordSpeech(isForce: Boolean) = if (trains.isEmpty()) "" else when (sourceLang) {
        ENGLISH -> if (answer.isEmpty() || isForce) trains.first.en else ""

        RUSSIAN -> if (answer.isNotEmpty()) {
            if (result(sourceLang, answer).toBoolean() == true) answer else trains.first.en
        } else ""

        UNKNOWN -> throw IllegalArgumentException("source lang is UNKNOWN")
    }

    override fun forSpeech(item: ItemTranslation): String {
        return if (sourceLang == RUSSIAN) item.translation else ""
    }

    override fun next() {
        if (trains.isNotEmpty()) trains.removeFirst()
        answer = ""
        variants.clear()

        if (trains.isNotEmpty()) variants.addAll(variants())
    }

    private suspend fun prepare(sourceLang: Language) {
        words.prepare() // TODO
        images.prepare() // TODO

        this.sourceLang = sourceLang

        ruEn.clear()
        enRu.clear()
        trains.clear()

        val all = words.words()
        all.forEach { word ->
            word.ru.forEach { ru ->
                if (ruEn[ru] == null) ruEn[ru] = HashSet()
                (ruEn[ru] as? MutableSet<String>)?.add(word.en)
            }

            if (enRu[word.en] == null) enRu[word.en] = HashSet()
            (enRu[word.en] as? MutableSet<String>)?.addAll(word.ru)

            transcriptions[word.en] = word.transcription
        }

        val count = settings.count(sourceLang.toTrainType())
        val ids = results.minResult(trainType(sourceLang), count)
        trains.addAll(all.filter { ids.contains(it.id) }.shuffled())

        variants.clear()
        variants.addAll(variants())
    }

    private fun trainType(sourceLang: Language) = (when (sourceLang) {
        ENGLISH -> TrainType.TRANSLATION_EN_RU
        RUSSIAN -> TrainType.TRANSLATION_RU_EN
        UNKNOWN -> throw IllegalArgumentException("source lang is UNKNOWN")
    })

    private fun translations(sourceLang: Language) = when (sourceLang) {
        ENGLISH -> enRu
        RUSSIAN -> ruEn
        UNKNOWN -> throw IllegalArgumentException("source lang is UNKNOWN")
    }

    private fun source(sourceLang: Language) = if (trains.isEmpty()) emptySet() else
        when (sourceLang) {
            ENGLISH -> setOf(trains.first.en)
            RUSSIAN -> trains.first.ru
            UNKNOWN -> throw IllegalArgumentException("source lang is UNKNOWN")
        }

    private fun transcription(en: String) = "[${transcriptions[en] ?: ""}]"

    private fun toItems(variants: List<String>) = variants.map { translation ->
        ItemTranslation(
            translation,
            if (sourceLang == RUSSIAN) transcription(translation) else "",
            sourceLang,
            "— ${translations(sourceLang.reverse())[translation]?.joinToString(", ") ?: ""}",
            resultItem(translation, answer)
        )
    }

    private fun resultItem(variant: String, answer: String) = when {
        answer.isEmpty() -> NONE
        answer == variant -> result(sourceLang, answer)
        result(sourceLang, variant).toBoolean() == true -> POSITIVE
        else -> NONE
    }

    private fun result(sourceLang: Language, answer: String): Result {
        return if (answer.isEmpty()) NONE else {
            val translations = translations(sourceLang)
            val result = source(sourceLang)
                .firstOrNull { translations[it]?.contains(answer) == true } != null
            Result.fromBoolean(result)
        }
    }

    private fun variants(): Set<String> {
        val translations = HashSet<String>(TRANSLATIONS)

        val correct = when (sourceLang) {
            ENGLISH -> if (trains.first.ru.size == 1) trains.first.ru.first() else
                trains.first.ru.toList().shuffled().first()

            RUSSIAN -> trains.first.en
            UNKNOWN -> throw IllegalStateException("source lang is UNKNOWN")
        }

        translations.add(correct)

        val all = when (sourceLang) {
            ENGLISH -> ruEn.keys
            RUSSIAN -> enRu.keys
            UNKNOWN -> throw IllegalStateException("source lang is UNKNOWN")
        }.let { ArrayList<String>(it) }.apply { remove(correct) }.shuffled()

        for (i in 0..minOf(TRANSLATIONS - 2, all.size - 1)) translations.add(all[i])
        return translations
    }

    private suspend fun imageUrl(source: Language, answer: String): String {
        val ru = when (source) {
            ENGLISH -> {
                if (answer.isNotEmpty() && result(sourceLang, answer) == POSITIVE) answer else
                    variants.intersect(trains.first.ru).firstOrNull() ?: ""
            }

            RUSSIAN -> source(sourceLang).first()
            UNKNOWN -> throw IllegalStateException("source lang is UNKNOWN")
        }
        return images.imageUrl(ru)
    }
}