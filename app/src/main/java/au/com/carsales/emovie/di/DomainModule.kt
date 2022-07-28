package au.com.carsales.emovie.di

import au.com.carsales.emovie.data.local.LocalMoviesRepositoryImpl
import au.com.carsales.emovie.data.local.dao.MoviesDao
import au.com.carsales.emovie.data.local.mapper.LocalDomainToEntityMovieMapper
import au.com.carsales.emovie.data.local.mapper.LocalEntityToDomainMovieMapper
import au.com.carsales.emovie.data.remote.RemoteMoviesService
import au.com.carsales.emovie.data.remote.RemoteMoviesRepositoryImpl
import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import au.com.carsales.emovie.data.remote.mapper.RemoteToEntityMovieMapper
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
        mapper: RemoteToDomainMovieMapper
    ) : RemoteMoviesRepositoryImpl {
        return RemoteMoviesRepositoryImpl(moviesService, mapper)
    }

    @Singleton
    @Provides
    fun provideLocalMoviesRepository(
        movieDao: MoviesDao,
        entityToDomainMovieMapper: LocalEntityToDomainMovieMapper,
        domainToEntityMovieMapper: LocalDomainToEntityMovieMapper
    ): LocalMoviesRepositoryImpl {
        return LocalMoviesRepositoryImpl(movieDao, entityToDomainMovieMapper, domainToEntityMovieMapper)
    }
}