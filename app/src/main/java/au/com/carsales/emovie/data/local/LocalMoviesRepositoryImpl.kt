package au.com.carsales.emovie.data.local

import au.com.carsales.emovie.data.local.dao.MoviesDao
import au.com.carsales.emovie.data.local.mapper.LocalDomainToEntityMovieMapper
import au.com.carsales.emovie.data.local.mapper.LocalEntityToDomainMovieMapper
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.repository.LocalMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalMoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val entityToDomainMovieMapper: LocalEntityToDomainMovieMapper,
    private val domainToEntityMovieMapper: LocalDomainToEntityMovieMapper
) : LocalMoviesRepository {

    override suspend fun getUpcomingMovies(): Flow<List<DomainMovieItem>> =
        flow {
            emit(entityToDomainMovieMapper.executeMapping(moviesDao.getUpcomingMovies()?.filterNotNull()).orEmpty())
        }

    override suspend fun insertMovies(movieList: List<DomainMovieItem>) {
        moviesDao.insertAll(domainToEntityMovieMapper.executeMapping(movieList).orEmpty())
    }
}