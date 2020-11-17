package com.presentation.core.extensions

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

fun Dialog.activityContext(): Context = (context as ContextThemeWrapper).baseContext

fun Dialog.activity() = activityContext() as AppCompatActivity

fun Dialog.fullscreen() {
    window?.fullscreen()
}

fun Dialog.softInputMode(mode: Int) {
    window?.softInputMode(mode)
}

fun Dialog.hideKeyboard() {
    window?.hideKeyboard()
}

fun Dialog.fullWidth() {
    window?.fullWidth()
}

fun Dialog.backgroundColor(colorRes: Int) {
    window?.backgroundColor(colorRes)
}

fun Dialog.safeShow() {
    val activity = activity()
    if (!activity.isFinishing && !activity.isDestroyed) {
        try {
            show()
        } catch (ex: WindowManager.BadTokenException) {
            Log.e(toString(), "Bad window token", ex)
        }
    } else Log.e(toString(), "failure show dialog activity is finishing or destroyed")
}

fun Dialog.inflater(): LayoutInflater = context.inflater()