package com.presentation.core.extensions

import android.util.DisplayMetrics
import android.view.WindowManager

fun WindowManager.screenWidth(): Int {
    val metrics = DisplayMetrics()
    defaultDisplay.getMetrics(metrics)
    return metrics.widthPixels
}

fun WindowManager.screenHeight(): Int {
    val metrics = DisplayMetrics()
    defaultDisplay.getMetrics(metrics)
    return metrics.heightPixels
}