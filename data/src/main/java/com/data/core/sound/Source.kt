package com.data.core.sound

import android.content.res.AssetFileDescriptor
import androidx.annotation.RawRes

data class Source(
    @RawRes val resourceId: Int? = null,
    val path: String? = null,
    val descriptor: AssetFileDescriptor? = null
)