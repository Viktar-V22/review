package com.domain.constructor

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.trains.TrainsResultRepository
import com.boundaries.base.trains.TrainsSettingsStore
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.core.common.Result
import com.core.common.TrainType.CONSTRUCTOR
import com.interactors.constructor.ItemLetter
import com.interactors.constructor.Letter
import com.interactors.constructor.State
import java.util.*
import javax.inject.Inject

class StateCaseImpl @Inject constructor(
    private val words: WordsRepository,
    private val images: ImagesRepository,
    private val results: TrainsResultRepository,
    private val settings: TrainsSettingsStore
) : StateCase {

    private val selected = ArrayDeque<Letter>()
    private val shuffled = ArrayDeque<Letter>()
    private val allWords = ArrayDeque<Word>()
    private var init = false

    override suspend fun state(): State {
        if (!init) prepare()

        return if (allWords.isEmpty()) State.Complete else {
            val word = allWords.first
            val selected = selected()

            State.Word(
                selected,
                word.ru.joinToString(", "),
                word.en,
                "[${word.transcription}]",
                imageUrl(),
                shuffled.map { ItemLetter(it, this.selected.contains(it)) },
                result(word, selected)
            )
        }
    }

    override suspend fun select(item: ItemLetter): Boolean {
        selected.add(item.letter)
        val isWordCompleted = selected.size == shuffled.size
        if (isWordCompleted) {
            val word = allWords.first
            results.store(word.id, CONSTRUCTOR, result(word, selected()))
        }

        return isWordCompleted
    }

    override fun undoOrNext() = if (selected.size == shuffled.size) next() else undo()

    override fun en() = if (allWords.isNotEmpty()) allWords.first.en else ""

    override fun isMute() = settings.isMute(CONSTRUCTOR)

    private fun next() {
        if (allWords.isNotEmpty()) allWords.removeFirst()
        selected.clear()
        fillShuffled()
    }

    private fun undo() {
        selected.removeLast()
    }

    private suspend fun prepare() {
        words.prepare() // TODO
        images.prepare() // TODO

        val count = settings.count(CONSTRUCTOR)
        val ids = results.minResult(CONSTRUCTOR, count)

        allWords.addAll(words.words(ids).shuffled())
        fillShuffled()

        init = true
    }

    private fun fillShuffled() {
        shuffled.clear()
        if (allWords.isNotEmpty()) shuffled.addAll(shuffled(allWords.first.en))
    }

    private fun shuffled(en: String) = en.toCharArray()
        .mapIndexed { index, char -> Letter(index, char.toString()) }
        .shuffled()

    private suspend fun imageUrl() = images.imageUrl(allWords.first.ru)

    private fun result(word: Word, selected: String) = when {
        selected.length != word.en.length -> Result.NONE
        selected == word.en -> Result.POSITIVE
        else -> Result.NEGATIVE
    }

    private fun selected() = selected.joinToString("") { it.letter }
}