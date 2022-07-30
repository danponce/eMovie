package au.com.carsales.emovie.data.remote

import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.domain.DomainMovieDataState
import au.com.carsales.emovie.domain.repository.RemoteMoviesRepository
import au.com.carsales.emovie.domain.utils.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteMoviesRepositoryImpl @Inject constructor(
    private val moviesService: RemoteMoviesService,
    private val toDomainMapper: RemoteToDomainMovieMapper
) : RemoteMoviesRepository {

    override suspend fun getUpcomingMovies(): Flow<DomainMovieDataState> =
        apiFlow(
            call = { moviesService.getUpcomingMovies() },
            validation = { movies ->  movies?.results != null },
            mapper = toDomainMapper,
            toMap = { movies -> movies?.results }
        )

    override suspend fun getTopRatedMovies(): Flow<DomainMovieDataState> =
        apiFlow(
            call = { moviesService.getTopRated() },
            validation = { movies ->  movies?.results != null },
            mapper = toDomainMapper,
            toMap = { movies -> movies?.results }
        )

}