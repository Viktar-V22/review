package com.lexicon.core.navigation

import android.content.Context
import javax.inject.Inject

class CloseAppImpl @Inject constructor() : CloseApp {

    override fun close(context: Context) {
        context.startActivity(CloseAppActivity.callingIntent(context))
    }
}