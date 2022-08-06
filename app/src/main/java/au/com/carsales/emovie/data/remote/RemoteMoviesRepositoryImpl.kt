package au.com.carsales.emovie.data.remote

import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieDetailMapper
import au.com.carsales.emovie.data.remote.mapper.RemoteToDomainMovieMapper
import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.domain.DomainMovieDataState
import au.com.carsales.emovie.domain.DomainMovieDetailDataState
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteMoviesRepositoryImpl @Inject constructor(
    private val moviesService: RemoteMoviesService,
    private val toDomainMapper: RemoteToDomainMovieMapper,
    private val movieDetailMapper: RemoteToDomainMovieDetailMapper
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

    override suspend fun getMovieDetail(movieId: String): Flow<DomainMovieDetailDataState> =
        flow {
            coroutineScope {
                val movie = async { moviesService.getMovieById(movieId) }
                val videos = async { moviesService.getMovieVideosById(movieId) }

                val movieDetailResponse = movie.await()
                val videoResponse = videos.await()

                when {
                    movieDetailResponse.isSuccessful && videoResponse.isSuccessful -> {
                        val detail: DomainMovieDetail = movieDetailMapper.executeMapping(
                            movieDetailResponse.body(),
                            videoResponse.body()
                        )

                        emit(APIState.Success(detail))
                    }

                    else -> {
                        if(!movieDetailResponse.isSuccessful) {
                            emit(APIState.Error(movieDetailResponse.message()))
                        } else {
                            emit(APIState.Error(videoResponse.message()))
                        }
                    }
                }

            }
        }.catch { emit(APIState.Error("API request error")) }.flowOn(Dispatchers.IO)

}