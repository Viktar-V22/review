package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.bindRefreshing(refreshing: Boolean) {
    isRefreshing = refreshing
}