package com.presentation.core.views

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseAdapter<T> : RecyclerView.Adapter<ViewHolder>() {

    abstract val data: MutableList<T>

    override fun getItemCount(): Int = data.size

    open fun getItem(position: Int): T = data.elementAt(position)

    open fun setItems(items: List<T>) {
        val callback = diffCallback(data, items)
        val diff = DiffUtil.calculateDiff(callback)
        data.run { clear(); addAll(items) }

        diff.dispatchUpdatesTo(this)
    }

    open fun diffCallback(old: List<T>, actual: List<T>): DiffUtil.Callback {
        throw NotImplementedError()
    }
}