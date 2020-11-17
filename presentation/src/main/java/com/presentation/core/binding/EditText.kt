package com.presentation.core.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text")
fun EditText.bindTextWithCursor(text: String?) {
    if (getText().toString() != text) {
        setText(text)
        text?.let { setSelection(it.length) }
    }
}