package com.danponce.emovie.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.danponce.emovie.data.local.LocalMoviesRepositoryImpl
import com.danponce.emovie.data.remote.RemoteMoviesRepositoryImpl
import com.danponce.emovie.domain.usecase.*
import com.danponce.emovie.utils.base.coroutines.CoroutinesContextProvider
import com.danponce.emovie.utils.datastore.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022. All rights reserved.
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

    @Provides
    fun provideGetMovieDetailUseCase(
        localRepository: LocalMoviesRepositoryImpl,
        remoteRepository: RemoteMoviesRepositoryImpl
    ) : GetMovieDetailUseCase {
        return GetMovieDetailUseCase(localRepository, remoteRepository)
    }

    @Provides
    fun provideAddFavoriteMovieUseCase(
        localRepository: LocalMoviesRepositoryImpl
    ) : AddFavoriteMovieUseCase {
        return AddFavoriteMovieUseCase(localRepository)
    }

    @Provides
    fun provideGetFavoriteMoviesUseCase(
        localRepository: LocalMoviesRepositoryImpl
    ) : GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(localRepository)
    }

    @Provides
    fun provideGetIsFavoriteMovieUseCase(
        localRepository: LocalMoviesRepositoryImpl
    ) : GetIsFavoriteMovieUseCase {
        return GetIsFavoriteMovieUseCase(localRepository)
    }

    @Provides
    fun provideDeleteFavoriteMovieUseCase(
        localRepository: LocalMoviesRepositoryImpl
    ) : DeleteFavoriteMovieUseCase {
        return DeleteFavoriteMovieUseCase(localRepository)
    }

    @Provides
    fun provideUserPreferencesRepository(
        preferencesDataStore: DataStore<Preferences>
    ) : UserPreferencesRepository {
        return UserPreferencesRepository(preferencesDataStore)
    }

    @Provides
    fun provideCoroutinesContextProvider() : CoroutinesContextProvider {
        return CoroutinesContextProvider()
    }
}