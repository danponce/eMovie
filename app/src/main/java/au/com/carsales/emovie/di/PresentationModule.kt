package au.com.carsales.emovie.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.migrations.SharedPreferencesMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import au.com.carsales.emovie.data.local.LocalMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.RemoteMoviesRepositoryImpl
import au.com.carsales.emovie.domain.usecase.*
import au.com.carsales.emovie.utils.datastore.UserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@InstallIn(ViewModelComponent::class)
@Module
object PresentationModule {

    private const val USER_PREFERENCES = "user_preferences"

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

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(appContext,USER_PREFERENCES)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { appContext.preferencesDataStoreFile(USER_PREFERENCES) }
        )
    }

    @Singleton
    @Provides
    fun provideUserPreferencesRepository(
        preferencesDataStore: DataStore<Preferences>
    ) : UserPreferencesRepository {
        return UserPreferencesRepository(preferencesDataStore)
    }
}