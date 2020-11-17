package com.presentation.core.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.Coil
import coil.load
import coil.transform.*
import com.presentation.R
import java.util.*

@BindingAdapter(
    "url",
    "placeholder",
    "placeholderRes",
    "errorHolder",
    "errorHolderRes",
    "blurRadius",
    "blurSampling",
    "circle",
    "grayscale",
    "leftTopCorner",
    "leftBottomCorner",
    "rightTopCorner",
    "rightBottomCorner",
    requireAll = false
)
fun ImageView.bindLoadByUrl(
    url: String?,
    placeholder: Drawable?,
    placeholderRes: Int?,
    errorHolder: Drawable?,
    errorHolderRes: Int?,
    blurRadius: Float?,
    blurSampling: Float?,
    circle: Boolean = false,
    grayscale: Boolean = false,
    leftTopCorner: Float?,
    leftBottomCorner: Float?,
    rightTopCorner: Float?,
    rightBottomCorner: Float?
) {
    if (url != null) load(url, Coil.imageLoader(context)) {

        placeholder?.apply { placeholder(this) }
            ?: placeholder(placeholderRes ?: R.drawable.bg_transparent)

        errorHolder?.apply { error(this) }
            ?: error(errorHolderRes ?: R.drawable.bg_transparent)

        val transformations = ArrayList<Transformation>()

        if (blurRadius != null || blurSampling != null) {
            transformations.add(BlurTransformation(context, blurRadius ?: 10f, blurSampling ?: 1f))
        }

        if (leftTopCorner != null || leftBottomCorner != null || rightTopCorner != null || rightBottomCorner != null) {
            val round = RoundedCornersTransformation(
                leftTopCorner ?: 0f,
                rightTopCorner ?: 0f,
                leftBottomCorner ?: 0f,
                rightBottomCorner ?: 0f
            )
            transformations.add(round)
        }

        if (circle) transformations.add(CircleCropTransformation())
        if (grayscale) transformations.add(GrayscaleTransformation())

        this.transformations(transformations)
    }
}