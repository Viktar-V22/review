package com.lexicon.core.navigation

import android.os.Bundle

interface Router {

    fun navigate(action: Int, params: Bundle = Bundle())

    fun closeApp()

    fun navigateUp()
}