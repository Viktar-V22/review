package com.data.core.speech

import android.content.Context
import com.core.common.createDir
import com.core.common.isExistAndNotEmpty
import com.core.common.recreate
import java.io.File
import javax.inject.Inject

class SpeechStoreImpl @Inject constructor(private val context: Context) : SpeechStore {

    companion object {
        private const val SYNTHESIZE_DIR = ".synthesize"
        private const val EXTENSION = ".mp3"
    }

    override suspend fun store(voice: String, text: String, byteArray: ByteArray): String {
        val dir = dir(voice)

        return if (dir == null || !file(dir, text).recreate()) {
            throw StoreSpeechFailed(voice, text)
        } else file(dir, text).apply { writeBytes(byteArray) }.absolutePath
    }

    override suspend fun filePath(voice: String, text: String): String? {
        val dir = dir(voice)

        return if (dir == null || !file(dir, text).isExistAndNotEmpty()) null else
            file(dir, text).absolutePath
    }

    private fun dir(voice: String) = context.filesDir.createDir(SYNTHESIZE_DIR)?.createDir(voice)

    private fun file(dir: File, text: String) = File("${dir.absolutePath}/$text$EXTENSION")
}