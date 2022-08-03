package au.com.carsales.emovie.di

import au.com.carsales.emovie.data.local.LocalMoviesRepositoryImpl
import au.com.carsales.emovie.data.local.dao.MovieDetailDao
import au.com.carsales.emovie.data.local.dao.MoviesDao
import au.com.carsales.emovie.data.local.dao.MoviesFavoritesDao
import au.com.carsales.emovie.data.local.mapper.*
import au.com.carsales.emovie.data.remote.RemoteMoviesService
import au.com.carsales.emovie.data.remote.RemoteMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieDetailMapper
import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Singleton
    @Provides
    fun provideRemoteMoviesRepository(
        moviesService: RemoteMoviesService,
        mapper: RemoteToDomainMovieMapper,
        movieDetailMapper: RemoteToDomainMovieDetailMapper
    ) : RemoteMoviesRepositoryImpl {
        return RemoteMoviesRepositoryImpl(moviesService, mapper, movieDetailMapper)
    }

    @Singleton
    @Provides
    fun provideLocalMoviesRepository(
        movieDao: MoviesDao,
        movieDetailDao: MovieDetailDao,
        moviesFavoritesDao: MoviesFavoritesDao,
        entityToDomainMovieMapper: LocalEntityToDomainMovieMapper,
        entityToDomainMovieDetailMapper: LocalEntityToDomainMovieDetailMapper,
        domainToEntityMovieDetailMapper: LocalDomainToEntityMovieDetailMapper,
        domainToEntityMovieMapper: LocalDomainToEntityMovieMapper,
        domainToEntityFavoriteMovieItemMapper: LocalDomainToEntityFavoriteMovieItemMapper
    ): LocalMoviesRepositoryImpl {
        return LocalMoviesRepositoryImpl(
            movieDao,
            movieDetailDao,
            moviesFavoritesDao,
            entityToDomainMovieMapper,
            entityToDomainMovieDetailMapper,
            domainToEntityMovieDetailMapper,
            domainToEntityMovieMapper,
            domainToEntityFavoriteMovieItemMapper
        )
    }
}