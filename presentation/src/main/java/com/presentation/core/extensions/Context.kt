package com.presentation.core.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.view.ContextThemeWrapper

fun Context.inflater(): LayoutInflater = LayoutInflater.from(this)

fun Context.inflater(theme: Int): LayoutInflater {
    return LayoutInflater.from(ContextThemeWrapper(this, theme))
}

fun Context.dpFromPx(px: Float): Int = (px / resources.displayMetrics.density).toInt()

fun Context.pxFromDp(dp: Float) = dp * resources.displayMetrics.density

fun Context.screenWidth(): Int = windowManager().screenWidth()

fun Context.screenHeight(): Int = windowManager().screenHeight()

fun Context.windowManager() = getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, message, duration)
}