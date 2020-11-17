package com.presentation.core.binding

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import com.core.common.Result
import com.google.android.material.textfield.TextInputLayout
import com.presentation.core.extensions.errorColor
import com.presentation.core.extensions.positiveColor
import com.presentation.core.extensions.reverseColor

@BindingAdapter("result")
fun TextInputLayout.bindResult(result: Result) {
    val color = when (result) {
        Result.POSITIVE -> context.positiveColor()
        else -> context.errorColor()
    }
    val errorState = ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))

    val textColor = when (result) {
        Result.POSITIVE -> context.positiveColor()
        Result.NEGATIVE -> context.errorColor()
        Result.NONE -> context.reverseColor()
    }
    val textState = ColorStateList(arrayOf(intArrayOf()), intArrayOf(textColor))

    editText?.setTextColor(textState)
    setErrorIconTintList(errorState)
    setErrorTextColor(errorState)
    boxStrokeErrorColor = errorState
    hintTextColor = textState
    setSuffixTextColor(textState)
}