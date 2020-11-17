package com.presentation.core.extensions

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView

fun TextView.addTextChangedListener(
    beforeTextChanged: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    onTextChanged: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> },
    afterTextChanged: (editable: Editable) -> Unit = {}
): TextWatcher = object : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        beforeTextChanged(s, start, count, after)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        onTextChanged(s, start, before, count)
    }

    override fun afterTextChanged(editable: Editable) = afterTextChanged(editable)

}.apply { addTextChangedListener(this) }