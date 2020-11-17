package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.presentation.core.views.BaseAdapter

@BindingAdapter("orientation")
fun ViewPager2.bindOrientation(orientation: Int) {
    this.orientation = orientation
}

@BindingAdapter("adapter", "items", requireAll = true)
fun <T> ViewPager2.bindItems(adapter: BaseAdapter<T>, items: List<T>) {
    if (this.adapter != adapter) this.adapter = adapter
    adapter.setItems(items)
}

@BindingAdapter("position")
fun ViewPager2.bindPosition(position: Int) {
    post { if (!isFakeDragging) setCurrentItem(position, false) }
}