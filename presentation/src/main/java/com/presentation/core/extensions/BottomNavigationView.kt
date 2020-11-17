package com.presentation.core.extensions

import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import com.google.android.material.bottomnavigation.BottomNavigationView

fun BottomNavigationView.slideUp() {
    val params = layoutParams

    val behavior = if (params is LayoutParams) {
        val behavior = params.behavior
        if (behavior is HideBottomViewOnScrollBehavior) behavior else null
    } else null

    behavior?.slideUp(this)
        ?: throw IllegalStateException("bottom navigation view must has got HideBottomViewOnScrollBehavior")
}

fun BottomNavigationView.slideDown() {
    val params = layoutParams

    val behavior = if (params is LayoutParams) {
        val behavior = params.behavior
        if (behavior is HideBottomViewOnScrollBehavior) behavior else null
    } else null

    behavior?.slideDown(this)
        ?: throw IllegalStateException("bottom navigation view must has got HideBottomViewOnScrollBehavior")
}