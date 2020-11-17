package com.presentation.core.extensions

import android.content.res.Resources
import android.util.TypedValue
import androidx.core.content.res.ResourcesCompat

fun Resources.isDrawable(res: Int): Boolean {
    val value = TypedValue()
    getValue(res, value, true)
    return value.string?.toString()?.substringAfterLast(".", "") ?: "" == "xml"
}

fun Resources.isColor(res: Int) = try {
    ResourcesCompat.getColor(this, res, null)
    true
} catch (ex: Resources.NotFoundException) {
    false
}

fun Resources.navBarHeight(): Int {
    val resId = getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resId > 0) getDimensionPixelSize(resId) else 0
}