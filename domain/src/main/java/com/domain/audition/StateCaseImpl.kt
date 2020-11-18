package com.domain.audition

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.core.common.Result
import com.core.common.TrainType.AUDITION
import com.interactors.audition.State
import javax.inject.Inject

class StateCaseImpl @Inject constructor(
    private val words: WordsRepository,
    private val images: ImagesRepository,
    private val results: TrainsResultRepository,
    private val settings: TrainsSettingsStore,
) : StateCase {

    private val trains = ArrayDeque<Word>()
    private var answer: String? = null

    override suspend fun state(): State {
        if (trains.isEmpty() && answer == null) prepare()
        val answer = this.answer

        return when {
            trains.isEmpty() -> State.Completed
            answer == null -> State.Question(images.imageUrl(trains.first().ru))

            else -> {
                val word = trains.first()
                State.Answer(
                    word.en,
                    "[${word.transcription}]",
                    word.ru.joinToString(", "),
                    result(word.en, answer)
                )
            }
        }
    }

    override fun forSpeech() = if (trains.isEmpty()) "" else trains.first().en

    override suspend fun checkOrNext(text: String) {
        if (answer == null) check(text) else next()
    }

    private suspend fun check(text: String) {
        answer = text

        if (trains.isNotEmpty()) with(trains.first()) {
            results.store(id, AUDITION, result(en, text))
        }
    }

    private fun next() {
        if (trains.isNotEmpty()) trains.removeFirst()
        answer = null
    }

    private suspend fun prepare() {
        words.prepare() // TODO
        images.prepare() // TODO

        val all = words.words()
        val count = settings.count(AUDITION)
        val ids = results.minResult(AUDITION, count)
        trains.addAll(all.filter { ids.contains(it.id) }.shuffled())
    }

    private fun result(correct: String, answer: String) = Result.fromBoolean(correct == answer)
}