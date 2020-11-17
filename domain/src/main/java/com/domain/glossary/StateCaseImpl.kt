package com.domain.glossary

import com.boundaries.base.images.ImagesRepository
import com.boundaries.base.words.Word
import com.boundaries.base.words.WordsRepository
import com.interactors.glossary.ItemWord
import javax.inject.Inject

class StateCaseImpl @Inject constructor(
    private val words: WordsRepository,
    // private val irregulars: IrregularsRepository,
    private val images: ImagesRepository
) : StateCase {

    private val urls = HashMap<String, String>()
    private val allWords = ArrayList<Word>()
    private var isCheck = false

    override suspend fun items(): List<ItemWord> {
        if (urls.isEmpty()) prepare()

        return allWords.map {
            ItemWord(
                it.id,
                imageUrl(it),
                it.en,
                it.ru.joinToString(", "),
                "[${it.transcription}]",
                if (!isCheck) null else it.enabled
            )
        }
    }

    override suspend fun reverseCheck(item: ItemWord) {
        item.isChecked?.let { words.enable(item.id, !it) }
    }

    override fun reverseCheck() {
        isCheck = !isCheck
    }

    private suspend fun prepare() {
        words.prepare() // TODO
        //irregulars.prepare() // TODO
        images.prepare() // TODO

        allWords.clear()
        allWords.addAll(words.words(null).sortedBy { it.en })

        urls.clear()
        val all = images.images()
        all.forEach { image -> image.ru.forEach { ru -> urls[ru] = image.imageUrl } }
    }

    private fun imageUrl(word: Word) = word.ru.firstOrNull { ru -> urls[ru] != null }
        ?.let { urls[it] } ?: images.noImageUrl()
}