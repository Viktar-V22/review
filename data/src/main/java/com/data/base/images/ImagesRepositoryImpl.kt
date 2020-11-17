package com.data.base.images

import android.content.res.AssetManager
import com.boundaries.base.images.Image
import com.boundaries.base.images.ImagesRepository
import com.data.base.db.DB.Constants.INIT_ID
import com.data.base.db.images.ImagesDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import com.data.base.db.images.Image as DbImage

class ImagesRepositoryImpl @Inject constructor(
    private val assets: AssetManager,
    private val images: ImagesDao
) : ImagesRepository {

    private companion object {
        private const val IMAGES_FILE = "ru_images.json"
        private const val IRREGULARS_FILE = "ru_irregulars.json"

        private const val NO_IMAGE_URL =
            "https://www.dropbox.com/s/03smog0rh9rbtk5/no_image.jpg?raw=1"
    }

    override suspend fun prepare() = withContext(Dispatchers.IO) {
        if (images.count() == 0) {
            val server = ArrayList<ServerImage>().apply {
                addAll(images(IMAGES_FILE))
                addAll(images(IRREGULARS_FILE))
            }
            images.store(server.map { DbImage(INIT_ID, it.ru, it.imageUrl) })
        }
    }

    override suspend fun images() = withContext(Dispatchers.IO) {
        images.getAll().map { Image(it.id, it.ru, it.url) }
    }

    override suspend fun imageUrl(ru: String) = images()
        .firstOrNull { it.ru.contains(ru) }?.imageUrl ?: NO_IMAGE_URL

    override suspend fun imageUrl(ru: Set<String>) = images()
        .firstOrNull { it.ru.intersect(ru).isNotEmpty() }?.imageUrl ?: NO_IMAGE_URL

    override fun noImageUrl() = NO_IMAGE_URL

    private suspend fun images(file: String) = withContext(Dispatchers.IO) {
        val raw = assets.open(file).use {
            it.bufferedReader().use { buffer -> buffer.readText() }
        }
        Json.decodeFromString<List<ServerImage>>(raw)
    }
}