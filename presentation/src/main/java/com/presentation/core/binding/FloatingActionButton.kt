package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("show")
fun FloatingActionButton.bindShow(isShow: Boolean) {
    if (isShow) show() else hide()
}