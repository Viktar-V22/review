package com.presentation.core.handlers

/*
fun MotionLayout.transition(): Observable<MotionTransition> = TransitionObservable(this)

private class TransitionObservable(private val view: MotionLayout) :
    Observable<MotionTransition>() {

    override fun subscribeActual(observer: Observer<in MotionTransition>) {
        val listener = TransitionsListener(view, observer)
        observer.onSubscribe(listener)
        view.setTransitionListener(listener)
    }
}

private class TransitionsListener(
    private val view: MotionLayout,
    private val observer: Observer<in MotionTransition>
) : MainThreadDisposable(), MotionLayout.TransitionListener {

    override fun onTransitionTrigger(p0: MotionLayout, p1: Int, p2: Boolean, p3: Float) {
        // do nothing
    }

    override fun onTransitionStarted(p0: MotionLayout, startId: Int, endId: Int) {
        if (!isDisposed) observer.onNext(MotionTransition.Started(startId, endId))
    }

    override fun onTransitionChange(p0: MotionLayout, startId: Int, endId: Int, progress: Float) {
        if (!isDisposed) observer.onNext(MotionTransition.Change(startId, endId, progress))
    }

    override fun onTransitionCompleted(p0: MotionLayout, endId: Int) {
        if (!isDisposed) observer.onNext(MotionTransition.Completed(endId))
    }

    override fun onDispose() {
        view.setTransitionListener(null)
    }
}

sealed class MotionTransition {
    data class Started(val startId: Int, val endId: Int) : MotionTransition()
    data class Change(val startId: Int, val endId: Int, val progress: Float) : MotionTransition()
    data class Completed(val endId: Int) : MotionTransition()
}*/
