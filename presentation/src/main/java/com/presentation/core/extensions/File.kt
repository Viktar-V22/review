package com.presentation.core.extensions

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun File.toUri(context: Context, authority: String): Uri {
    return FileProvider.getUriForFile(context, authority, this)
}
