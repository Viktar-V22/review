package com.presentation.core.handlers

import android.widget.CompoundButton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate

@ExperimentalCoroutinesApi
fun CompoundButton.checkChanges(): Flow<Boolean> = callbackFlow {
    val listener = CompoundButton.OnCheckedChangeListener { _, isChecked -> offer(isChecked) }
    setOnCheckedChangeListener(listener)
    awaitClose { setOnCheckedChangeListener(null) }
}.conflate()

/*
fun CompoundButton.checkChanges(): Observable<Boolean> = SwitchObservable(this)

private class SwitchObservable(private val view: CompoundButton) : Observable<Boolean>() {

    override fun subscribeActual(observer: Observer<in Boolean>) {
        val listener = SwitchListener(view, observer)
        observer.onSubscribe(listener)
        view.setOnCheckedChangeListener(listener)
    }
}

private class SwitchListener(
    private val view: CompoundButton,
    private val observer: Observer<in Boolean>
) : MainThreadDisposable(), CompoundButton.OnCheckedChangeListener {

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (!isDisposed) observer.onNext(isChecked)
    }

    override fun onDispose() {
        view.setOnCheckedChangeListener(null)
    }
}*/
