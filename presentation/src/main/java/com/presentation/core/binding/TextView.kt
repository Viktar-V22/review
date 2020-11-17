package com.presentation.core.binding

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.Typeface.BOLD
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("editorActionListener")
fun TextView.bindKeyListener(listener: TextView.OnEditorActionListener) {
    setOnEditorActionListener(listener)
}

@BindingAdapter(value = ["fullText", "boldText"], requireAll = true)
fun TextView.bindBoldText(fullText: String, boldText: String) {
    text = if (fullText.contains(boldText)) {
        val boldLength = boldText.length
        val builder = SpannableStringBuilder(fullText)
        var start = 0
        fullText.split(boldText).forEach {
            start += it.length
            val end = start + boldLength
            if (end <= fullText.length) {
                builder.setSpan(StyleSpan(BOLD), start, end, SPAN_EXCLUSIVE_EXCLUSIVE)
                start = end
            }
        }

        builder
    } else fullText
}

@BindingAdapter(value = ["drawableEnd", "drawableTintEnd"], requireAll = true)
fun TextView.bindDrawableEndTint(drawable: Drawable, color: Int) {
    val updated = drawable.mutate()
        .apply { colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN) }
    val drawables = compoundDrawables
    setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], updated, drawables[3])
}

@BindingAdapter("crossLine")
fun TextView.bindCrossLine(crossLine: Boolean) {
    paintFlags = if (crossLine) (paintFlags or STRIKE_THRU_TEXT_FLAG) else
        (paintFlags and STRIKE_THRU_TEXT_FLAG.inv())
}