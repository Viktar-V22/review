package com.presentation.core.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.presentation.core.extensions.colorAttr
import com.presentation.core.views.ColorAttr
import com.presentation.core.views.ColorInteger
import com.presentation.core.views.ColorResource
import com.presentation.core.views.ColorWrapper

@BindingAdapter(value = ["imageRes", "drawable"], requireAll = false)
fun ImageView.bindImage(imageRes: Int?, drawable: Drawable?) {
    val icon = drawable ?: imageRes?.let { if (it > 0) getDrawable(context, imageRes) else null }
    setImageDrawable(icon)
}


@BindingAdapter("tintColor")
fun ImageView.bindTintColor(color: ColorWrapper) {
    setColorFilter(
        when (color) {
            is ColorAttr -> context.colorAttr(color.colorAttr())
            is ColorResource -> ContextCompat.getColor(context, color.colorRes())
            is ColorInteger -> color.colorInt()
            else -> ContextCompat.getColor(context, android.R.color.transparent)
        }
    )
}