package com.presentation.core.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.presentation.core.extensions.slideDown
import com.presentation.core.extensions.slideUp

@BindingAdapter("slideUp")
fun BottomNavigationView.bindSlideUp(slideUp: Boolean) {
    if (slideUp) slideUp() else slideDown()
}