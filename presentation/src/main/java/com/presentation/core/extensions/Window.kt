package com.presentation.core.extensions

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.view.WindowManager.LayoutParams.MATCH_PARENT
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import androidx.core.content.ContextCompat

fun Window.fullscreen() {
    setLayout(MATCH_PARENT, MATCH_PARENT)
    clearFlags(FLAG_DIM_BEHIND)
}

fun Window.softInputMode(mode: Int) {
    setSoftInputMode(mode)
}

fun Window.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let { imm.hideSoftInputFromWindow(it.windowToken, HIDE_NOT_ALWAYS) }
}

fun Window.fullWidth() {
    setLayout(MATCH_PARENT, attributes.height)
    clearFlags(FLAG_DIM_BEHIND)
}

fun Window.backgroundColor(colorRes: Int) {
    setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(context, colorRes)))
}