package com.lexicon.di.base.images

import com.boundaries.base.images.ImagesRepository
import com.data.base.images.ImagesRepositoryImpl
import com.lexicon.di.FeatureScope
import dagger.Binds
import dagger.Module

@Module
interface ImagesModule {

    @Binds
    @FeatureScope
    fun provideImagesRepository(repository: ImagesRepositoryImpl): ImagesRepository
}