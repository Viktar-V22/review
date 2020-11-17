package com.core.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import com.core.common.createDir
import java.io.File

fun Context.createCacheDir(name: String): File? = cacheDir.createDir(name)

fun Context.versionCode(): Long {
    val info = packageManager.getPackageInfo(packageName, 0)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        info.longVersionCode
    } else info.versionCode.toLong()
}

fun Context.versionName(): String = packageManager.getPackageInfo(packageName, 0).versionName

fun Context.connectivityManager(): ConnectivityManager {
    return applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

fun Context.assetFileDescriptor(name: String) = assets.openFd(name)

fun Context.uriFromRaw(id: Int): Uri = Uri.parse("android.resource://${packageName}/$id")