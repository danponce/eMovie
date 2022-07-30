package au.com.carsales.emovie.di

import au.com.carsales.emovie.data.local.LocalMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.RemoteMoviesRepositoryImpl
import au.com.carsales.emovie.domain.usecase.GetTopRatedMoviesUseCase
import au.com.carsales.emovie.domain.usecase.GetUpcomingMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@InstallIn(ViewModelComponent::class)
@Module
object PresentationModule {

    @Provides
    fun provideGetLatestMoviesUseCase(
        localRepository: LocalMoviesRepositoryImpl,
        remoteRepository: RemoteMoviesRepositoryImpl
    ) : GetUpcomingMoviesUseCase {
        return GetUpcomingMoviesUseCase(localRepository, remoteRepository)
    }

    @Provides
    fun provideGetTopRatedMoviesUseCase(
        localRepository: LocalMoviesRepositoryImpl,
        remoteRepository: RemoteMoviesRepositoryImpl
    ) : GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCase(localRepository, remoteRepository)
    }
}