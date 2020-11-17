package com.presentation.core.extensions

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment

fun Activity.hideKeyboard() {
    window.hideKeyboard()
}

fun Fragment.hideKeyboard() {
    window().hideKeyboard()
}

fun EditText.showKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}