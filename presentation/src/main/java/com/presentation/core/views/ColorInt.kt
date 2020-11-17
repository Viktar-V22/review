package com.presentation.core.views

import androidx.annotation.ColorInt

data class ColorInt(@ColorInt val color: Int) : ColorInteger {
    override fun colorInt() = color
}