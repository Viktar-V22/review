package com.presentation.trains

import androidx.recyclerview.widget.DiffUtil
import com.interactors.trains.ItemTrain

class TrainsDiff(
    private val old: List<ItemTrain>,
    private val actual: List<ItemTrain>
) : DiffUtil.Callback() {

    override fun getOldListSize() = old.size

    override fun getNewListSize() = actual.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int): Boolean {
        return old[oldPos].train == actual[newPos].train
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int) : Boolean {
        val old = old[oldPos]
        val actual = actual[newPos]

        return old.isMute == actual.isMute && old.count == actual.count
    }
}