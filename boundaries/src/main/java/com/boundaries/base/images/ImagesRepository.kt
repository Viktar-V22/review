package com.boundaries.base.images

interface ImagesRepository {

    suspend fun prepare()

    suspend fun images(): List<Image>

    suspend fun imageUrl(ru: String): String

    suspend fun imageUrl(ru: Set<String>): String

    fun noImageUrl(): String
}