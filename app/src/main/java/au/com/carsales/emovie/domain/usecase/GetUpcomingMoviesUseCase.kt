package au.com.carsales.emovie.domain.usecase

import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.repository.LocalMoviesRepository
import au.com.carsales.emovie.domain.repository.RemoteMoviesRepository
import au.com.carsales.emovie.domain.utils.resultFlow
import au.com.carsales.emovie.utils.base.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetUpcomingMoviesUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository
) {

    suspend fun getUpcomingMovies() : Flow<State<List<DomainMovieItem>>> =
        resultFlow(
            databaseQuery = { localMoviesRepository.getUpcomingMovies() },
            networkCall = { remoteMoviesRepository.getUpcomingMovies() },
            saveCallResult = { movies -> localMoviesRepository.insertMovies(movies) }
        )

}