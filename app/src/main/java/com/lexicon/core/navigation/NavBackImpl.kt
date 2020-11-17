package com.lexicon.core.navigation

import androidx.fragment.app.Fragment
import javax.inject.Inject

class NavBackImpl @Inject constructor() : NavBack {

    override fun back(f: Fragment): Int {
        return 0
    }
}