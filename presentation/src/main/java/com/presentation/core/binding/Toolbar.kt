package com.presentation.core.binding

import android.widget.Toolbar
import androidx.databinding.BindingAdapter

@BindingAdapter("title")
fun Toolbar.bindTitle(title: String) {
    setTitle(title)
}