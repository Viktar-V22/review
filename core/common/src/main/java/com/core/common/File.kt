package com.core.common

import java.io.*


fun File.createDir(name: String): File? {
    val dir = File(this, name)

    return when {
        dir.exists() -> dir
        dir.mkdirs() -> dir
        else -> null
    }
}

fun File.createFile(name: String): File? {
    val file = File(this, name)

    return if (file.exists()) file else try {
        if (file.createNewFile()) file else null
    } catch (ex: IOException) {
        null
    }
}

fun File.isExistAndNotEmpty(): Boolean = exists() && length() != 0L

fun File.recreate() = when {
    isExistAndNotEmpty() -> delete() && createNewFile()
    !exists() -> createNewFile()
    else -> true
}

fun File.setExtension(extension: String): Boolean {
    val index = name.lastIndexOf(".")
    val current = name.substring(index)
    return if (current != extension) {
        renameTo(File(name.substring(0, name.length - current.length)))
    } else true
}

fun File.toOutputStream(): OutputStream? = try {
    FileOutputStream(this)
} catch (ex: FileNotFoundException) {
    null
}

fun String.toFile(): File = File(this)

fun String.isFileExists() = toFile().exists()

fun String.isFileEmpty(): Boolean = !toFile().isExistAndNotEmpty()

fun String.isFileExistAndNotEmpty(): Boolean = !isFileEmpty()

fun String.deleteFile(): Boolean = toFile().delete()