package com.presentation.core.views

import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import java.io.File

interface LoadImage {

    fun load(url: String)

    fun load(file: File)

    fun load(drawable: Drawable)

    fun load(res: Int)

    fun failure(file: File): LoadImage

    fun failure(drawable: Drawable): LoadImage

    fun failure(res: Int): LoadImage

    fun placeholder(file: File): LoadImage

    fun placeholder(drawable: Drawable): LoadImage

    fun placeholder(res: Int): LoadImage

    fun scaleType(type: Int): LoadImage

    fun circle(isCircle: Boolean): LoadImage

    fun corners(@DimenRes cornersRes: Int): LoadImage

    fun corners(corners: Float): LoadImage

    fun corners(@DimenRes topLeft: Int, @DimenRes topRight: Int, @DimenRes bottomRight: Int, @DimenRes bottomLeft: Int): LoadImage

    fun corners(topLeft: Float, topRight: Float, bottomRight: Float, bottomLeft: Float): LoadImage

    fun borderColorRes(@ColorRes colorRes: Int): LoadImage

    fun borderColor(@ColorInt color: Int): LoadImage

    fun borderWidthRes(@DimenRes widthRes: Int): LoadImage

    fun borderWidth(width: Float): LoadImage
}