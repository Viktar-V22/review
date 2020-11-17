package com.presentation.core.handlers

import android.text.Editable
import android.widget.TextView
import com.presentation.core.extensions.addTextChangedListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.debounce

@FlowPreview
@ExperimentalCoroutinesApi
fun TextView.afterTextChanged(debounce: Long) = afterTextChanged().debounce(debounce)

@ExperimentalCoroutinesApi
fun TextView.afterTextChanged() = callbackFlow {
    val afterTextChanged: (Editable) -> Unit = { text -> offer(text.toString()) }
    val changeListener = addTextChangedListener(afterTextChanged = afterTextChanged)
    awaitClose { removeTextChangedListener(changeListener) }
}.conflate()

/*
fun TextView.textChanges(): Observable<CharSequence> {
    return TextChangeObservable(this)
}

private class TextChangeObservable(private val view: TextView) : Observable<CharSequence>() {

    override fun subscribeActual(observer: Observer<in CharSequence>) {
        val listener = TextChangeListener(view, observer)
        observer.onSubscribe(listener)
        view.addTextChangedListener(listener)
    }
}

private class TextChangeListener(
    private val view: TextView,
    private val observer: Observer<in CharSequence>
) : MainThreadDisposable(), TextWatcher {

    override fun afterTextChanged(s: Editable) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (!isDisposed) observer.onNext(s)
    }

    override fun onDispose() {
        view.removeTextChangedListener(this)
    }
}*/
