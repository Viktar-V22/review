package com.presentation.core.binding

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.appbar.AppBarLayout
import com.presentation.core.extensions.navBarHeight
import com.presentation.core.extensions.screenHeight
import com.presentation.core.extensions.showKeyboard

@BindingAdapter("requestFocus")
fun View.bindRequestFocus(isRequest: Boolean) {
    post {
        if (isRequest) {
            requestFocus()

            if (this is EditText) showKeyboard()
        } else clearFocus()
    }
}

@BindingAdapter("enableScrollBehaviour")
fun View.bindEnableScrollBehaviour(enabled: Boolean) {
    val params = layoutParams
    if (params is CoordinatorLayout.LayoutParams) {
        params.behavior = if (enabled) AppBarLayout.ScrollingViewBehavior() else null
    }
}

@BindingAdapter("selected")
fun View.bindSelected(isSelected: Boolean) {
    this.isSelected = isSelected
}

@BindingAdapter("android:layout_marginStart")
fun View.bindMarginStart(margin: Float) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.marginStart = margin.toInt()
    }
}

@BindingAdapter("android:layout_marginTop")
fun View.bindMarginTop(margin: Float) {
    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.topMargin = margin.toInt()
    }
}

// not remove. lint is lie. the attribute will not ignored
@BindingAdapter("app:layout_constraintVertical_bias")
fun View.bindVerticalBias(bias: Float) {
    val params = layoutParams
    if (params is ConstraintLayout.LayoutParams) {
        params.verticalBias = bias
    }
}

@BindingAdapter("canFillToBottom")
fun View.bindCanFillToBottom(fill: Boolean) = post {
    val params = layoutParams
    if (fill && params is ConstraintLayout.LayoutParams) {
        val max = context.screenHeight() - resources.navBarHeight() - top - params.bottomMargin
        if (max != params.matchConstraintMaxHeight) layoutParams = params
            .apply { matchConstraintMaxHeight = max }
    }
}

@BindingAdapter("layout_width", "layout_height", requireAll = false)
fun View.bindSize(width: Float, height: Float) {
    if (layoutParams.width != width.toInt()) layoutParams.width = width.toInt()
    if (layoutParams.height != height.toInt()) layoutParams.height = height.toInt()
}

@BindingAdapter("backgroundRes")
fun View.bindBackgroundRes(backgroundRes: Int) {
    setBackgroundResource(backgroundRes)
}