package com.kohlwage.boschkraken.di

import com.kohlwage.boschkraken.pictures.GlideLoader
import com.kohlwage.boschkraken.pictures.PictureLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface PictureModule {

    @Binds
    @Singleton
    fun bindPictureLoader(pictureLoader: GlideLoader): PictureLoader

}