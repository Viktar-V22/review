package com.presentation.constructor

import androidx.recyclerview.widget.DiffUtil
import com.interactors.constructor.ItemLetter

class LettersDiff(
    private val old: List<ItemLetter>,
    private val actual: List<ItemLetter>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = actual.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].letter.id == actual[newPos].letter.id
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].selected == actual[newPos].selected
    }
}