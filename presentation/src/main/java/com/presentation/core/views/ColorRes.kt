package com.presentation.core.views

import androidx.annotation.ColorRes

data class ColorRes(@ColorRes val color: Int) : ColorResource {
    override fun colorRes() = color
}