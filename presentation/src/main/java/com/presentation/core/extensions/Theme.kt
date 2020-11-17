package com.presentation.core.extensions

import android.content.Context
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.presentation.R

@ColorInt
fun Context.errorColor() = colorAttr(R.attr.errorColor)

@ColorInt
fun Context.positiveColor() = colorAttr(R.attr.positiveColor)

@ColorInt
fun Context.reverseColor() = colorAttr(R.attr.reverseColor)

@ColorInt
fun Context.colorAttr(@AttrRes attr: Int): Int {
    val typed = TypedValue()
    theme.resolveAttribute(attr, typed, true)
    return typed.data
}