package com.presentation.core.keyboard

import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class KeyboardDetector private constructor(private val window: Window) {

    companion object {
        // 0.15 ratio is perhaps enough to determine keypad height.
        private const val KEYBOARD_SCREEN_RATIO = 0.15

        fun with(window: Window) = KeyboardDetector(window)
    }

    @ExperimentalCoroutinesApi
    fun detect(): Flow<Boolean> = callbackFlow {
        var root = window.findViewById<View>(android.R.id.content).rootView
        val windowRect = Rect()

        var listener: OnGlobalLayoutListener? = OnGlobalLayoutListener {
            root.let {
                it.getWindowVisibleDisplayFrame(windowRect)
                val screenHeight = it.height
                // r.bottom is the position above soft keyboard or device button.
                // if keyboard is shown, the r.bottom is smaller than that before.
                val keyboardHeight = screenHeight - windowRect.bottom
                offer(keyboardHeight > screenHeight * KEYBOARD_SCREEN_RATIO)
            }
        }

        root.viewTreeObserver.addOnGlobalLayoutListener(listener)

        awaitClose {
            root.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            listener = null
            root = null
        }
    }.distinctUntilChanged()
}

/*
class KeyboardDetector private constructor(private val window: Window) {

    companion object {
        // 0.15 ratio is perhaps enough to determine keypad height.
        private const val KEYBOARD_SCREEN_RATIO = 0.15

        fun with(window: Window): KeyboardDetector {
            return KeyboardDetector(window)
        }
    }

    fun detect(): Observable<Boolean> = Observable.create<Boolean> { emitter ->
        var root = window.findViewById<View>(android.R.id.content).rootView
        val windowRect = Rect()

        var listener: OnGlobalLayoutListener? = OnGlobalLayoutListener {
            root.let {
                it.getWindowVisibleDisplayFrame(windowRect)
                val screenHeight = it.height
                // r.bottom is the position above soft keyboard or device button.
                // if keyboard is shown, the r.bottom is smaller than that before.
                val keyboardHeight = screenHeight - windowRect.bottom
                emitter.onNext(keyboardHeight > screenHeight * KEYBOARD_SCREEN_RATIO)
            }
        }

        root.viewTreeObserver.addOnGlobalLayoutListener(listener)

        emitter.setCancellable {
            root.viewTreeObserver.removeOnGlobalLayoutListener(listener)
            listener = null
            root = null
        }
    }.distinctUntilChanged()
}*/
