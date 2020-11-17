package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout

@BindingAdapter("smoothExpand")
fun AppBarLayout.bindExpand(expand: Boolean) {
    setExpanded(expand, true)
}