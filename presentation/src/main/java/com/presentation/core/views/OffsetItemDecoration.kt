package com.presentation.core.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OffsetItemDecoration(
    private val vertical: Int = 0,
    private val horizontal: Int = 0
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)

        if (parent.layoutManager == null) parent.post { apply(position, outRect, parent) } else
            apply(position, outRect, parent)
    }

    private fun apply(position: Int, outRect: Rect, parent: RecyclerView) {
        val layoutManager = parent.layoutManager
        val columns = if (layoutManager is GridLayoutManager) layoutManager.spanCount else 1

        val row = position / columns
        val column = position % columns

        if (columns == 1) {
            outRect.left = horizontal
            outRect.right = horizontal
        } else {
            outRect.left = horizontal - column * horizontal / columns
            outRect.right = (column + 1) * horizontal / columns
        }

        outRect.top = if (row == 0) vertical else 0
        outRect.bottom = vertical
    }
}