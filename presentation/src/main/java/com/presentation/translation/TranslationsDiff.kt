package com.presentation.translation

import androidx.recyclerview.widget.DiffUtil
import com.interactors.translation.ItemTranslation

class TranslationsDiff(
    private val old: List<ItemTranslation>,
    private val actual: List<ItemTranslation>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = actual.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].translation == actual[newPos].translation
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].result == actual[newPos].result
    }
}