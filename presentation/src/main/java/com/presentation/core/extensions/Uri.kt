package com.presentation.core.extensions

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import com.core.common.safeLet
import com.core.common.toOutputStream
import java.io.*

fun Uri.fileMimeType(context: Context): String? = fileMimeType(context.contentResolver)

fun Uri.fileMimeType(resolver: ContentResolver): String? {
    return if (scheme == ContentResolver.SCHEME_CONTENT) {
        val mime = MimeTypeMap.getSingleton()
        resolver.getType(this)?.let { mime.getExtensionFromMimeType(it) }
    } else MimeTypeMap.getFileExtensionFromUrl(path)
}

fun Uri.toInputStream(context: Context): InputStream? = toInputStream(context.contentResolver)

fun Uri.toInputStream(resolver: ContentResolver): InputStream? = try {
    resolver.openInputStream(this)
} catch (ex: FileNotFoundException) {
    null
}

fun Uri.toOutputStream(context: Context): OutputStream? = toOutputStream(context.contentResolver)

fun Uri.toOutputStream(resolver: ContentResolver): OutputStream? = try {
    resolver.openOutputStream(this)
} catch (ex: FileNotFoundException) {
    null
}

fun Uri.copyToFile(context: Context, file: File): Boolean {
    val inputStream = toInputStream(context)
    val outputStream = file.toOutputStream()

    return safeLet(inputStream, outputStream) { input, output ->
        try {
            input.use { iStream -> output.use { oStream -> iStream.copyTo(oStream) } }
            true
        } catch (ex: IOException) {
            false
        }
    } ?: false
}