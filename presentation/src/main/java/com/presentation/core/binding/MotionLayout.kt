package com.presentation.core.binding

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter

@BindingAdapter("transition")
fun MotionLayout.bindTransition(toEnd: Boolean) {
    if (toEnd) transitionToEnd() else transitionToStart()
}

@BindingAdapter("scene")
fun MotionLayout.bindScene(sceneRes: Int) {
    loadLayoutDescription(sceneRes)
}