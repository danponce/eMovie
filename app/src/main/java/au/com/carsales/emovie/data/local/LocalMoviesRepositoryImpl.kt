package au.com.carsales.emovie.data.local

import au.com.carsales.emovie.data.local.dao.MovieDetailDao
import au.com.carsales.emovie.data.local.dao.MoviesDao
import au.com.carsales.emovie.data.local.mapper.LocalDomainToEntityMovieDetailMapper
import au.com.carsales.emovie.data.local.mapper.LocalDomainToEntityMovieMapper
import au.com.carsales.emovie.data.local.mapper.LocalEntityToDomainMovieDetailMapper
import au.com.carsales.emovie.data.local.mapper.LocalEntityToDomainMovieMapper
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.repository.LocalMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalMoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val movieDetailDao: MovieDetailDao,
    private val entityToDomainMovieMapper: LocalEntityToDomainMovieMapper,
    private val entityToDomainMovieDetailMapper: LocalEntityToDomainMovieDetailMapper,
    private val domainToEntityMovieDetailMapper: LocalDomainToEntityMovieDetailMapper,
    private val domainToEntityMovieMapper: LocalDomainToEntityMovieMapper
) : LocalMoviesRepository {

    override suspend fun getUpcomingMovies(): Flow<List<DomainMovieItem>> =
        flow {
            moviesDao.getUpcomingMovies().collect {
                emit(entityToDomainMovieMapper.executeMapping(it?.filterNotNull()).orEmpty())
            }
        }

    override suspend fun getAllMovies(): Flow<List<DomainMovieItem>> =
        flow {
            moviesDao.getAllMovies().collect {
                emit(entityToDomainMovieMapper.executeMapping(it?.filterNotNull()).orEmpty())
            }
        }

    override suspend fun insertMovies(movieList: List<DomainMovieItem>) {
        moviesDao.insertAll(domainToEntityMovieMapper.executeMapping(movieList).orEmpty())
    }

    override suspend fun getTopRatedMovies(): Flow<List<DomainMovieItem>> =
        flow {
            moviesDao.getTopRatedMovies().collect {
                emit(entityToDomainMovieMapper.executeMapping(it?.filterNotNull()).orEmpty())
            }
        }

    override suspend fun getMovie(id: String): Flow<DomainMovieDetail> =
        flow {
            movieDetailDao.getMovieDetail(id).collect {
                emit(entityToDomainMovieDetailMapper.executeMapping(it))
            }
        }

    override suspend fun insertMovieDetail(movieDetail: DomainMovieDetail) {
        movieDetailDao.addMovieDetail(domainToEntityMovieDetailMapper.executeMapping(movieDetail))
    }
}