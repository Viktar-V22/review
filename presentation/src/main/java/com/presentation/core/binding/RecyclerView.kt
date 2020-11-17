package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.presentation.core.views.BaseAdapter
import com.presentation.core.views.OffsetItemDecoration

@BindingAdapter("orientation", "spanCount", requireAll = false)
fun RecyclerView.bindOrientation(orientation: Int, spanCount: Int?) {
    layoutManager = if (spanCount == null || spanCount == 0) {
        LinearLayoutManager(context).also { it.orientation = orientation }
    } else GridLayoutManager(context, spanCount, orientation, false)
}

@BindingAdapter("items", "adapter", requireAll = false)
fun <T> RecyclerView.bindItems(items: List<T>?, adapter: BaseAdapter<T>?) {
    if (this.adapter != adapter) this.adapter = adapter
    items?.let { adapter?.setItems(it) }
}

@BindingAdapter("changeAnimation")
fun RecyclerView.bindDisableAnimation(changeAnimations: Boolean) {
    (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = changeAnimations
    if (!changeAnimations) itemAnimator?.changeDuration = 0
}

@BindingAdapter("scrollTo")
fun RecyclerView.bindScrollTo(position: Int) {
    post { scrollToPosition(position) }
}

@BindingAdapter("offsetVertical", "offsetHorizontal", requireAll = false)
fun RecyclerView.bindOffset(vertical: Float = 0f, horizontal: Float = 0f) {
    addItemDecoration(OffsetItemDecoration(vertical.toInt(), horizontal.toInt()))
}