package com.lexicon.main

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.lexicon.di.main.MainComponent
import com.lexicon.di.main.MainInjector

class MainViewModel(
    private val injector: MainInjector,
    private val component: MainComponent
) : ViewModel() {

    fun inject(fragment: Fragment) = injector.inject(fragment, component)

    fun inject(activity: MainActivity) = component.inject(activity)
}