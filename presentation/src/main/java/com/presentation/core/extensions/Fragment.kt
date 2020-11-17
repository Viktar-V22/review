package com.presentation.core.extensions

import android.content.Context
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

fun Fragment.window(): Window = requireActivity().window

fun Fragment.appContext(): Context = requireActivity().applicationContext

fun <T : ViewDataBinding> Fragment.requireBinding(): T {
    return DataBindingUtil.getBinding<T>(requireView())!!
}