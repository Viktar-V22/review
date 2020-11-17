package com.presentation.core.extensions

import androidx.databinding.ObservableList

fun <T> ObservableList<T>.setAll(items: Collection<T>) {
    (this as MutableList<T>).clear()
    addAll(items)
}