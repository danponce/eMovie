package au.com.carsales.emovie.data.remote

import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import au.com.carsales.emovie.data.remote.mapper.RemoteToEntityMovieMapper
import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.domain.DomainMovieDataState
import au.com.carsales.emovie.domain.repository.RemoteMoviesRepository
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

    override suspend fun getUpcomingMovies(): Flow<DomainMovieDataState> {

        return flow {
            val response = moviesService.getUpcomingMovies()

            when {
                response.isSuccessful -> {
                    response.body()?.let { movies ->
                            checkNotNull(movies.results) {
                                emit(APIState.Error("No movies available"))
                                return@let
                            }

                            emit(
                                APIState.Success(toDomainMapper.executeMapping(movies.results).orEmpty())
                            )
                    } ?: emit(APIState.Empty(response.message()))
                }

                else -> emit(APIState.Error(response.message()))
            }

        }
    }
}