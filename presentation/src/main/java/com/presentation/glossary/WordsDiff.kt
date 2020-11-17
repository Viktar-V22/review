package com.presentation.glossary

import androidx.recyclerview.widget.DiffUtil
import com.interactors.glossary.ItemWord

class WordsDiff(
    private val old: List<ItemWord>,
    private val actual: List<ItemWord>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = actual.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].id == actual[newPos].id
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].isChecked == actual[newPos].isChecked
    }
}