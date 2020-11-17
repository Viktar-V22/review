package com.lexicon.core.navigation

import androidx.fragment.app.Fragment

interface NavBack {

    fun back(f: Fragment): Int
}