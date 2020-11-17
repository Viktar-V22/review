package com.lexicon.core.navigation

import android.content.Context
import androidx.navigation.NavController

interface NavBinder {

    fun bind(context: Context, controller: NavController)

    fun unbind()
}