package com.presentation.core.extensions

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.postScroll(position: Int, after: () -> Unit = {}) {
    post { scrollToPosition(position); after() }
}

fun RecyclerView.clearItemDecorations() {
    if (itemDecorationCount != 0) for (i in 0..itemDecorationCount) removeItemDecorationAt(i)
}