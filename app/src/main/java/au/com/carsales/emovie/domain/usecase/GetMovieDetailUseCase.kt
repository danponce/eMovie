package au.com.carsales.emovie.domain.usecase

import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.repository.LocalMoviesRepository
import au.com.carsales.emovie.domain.repository.RemoteMoviesRepository
import au.com.carsales.emovie.domain.utils.resultFlow
import au.com.carsales.emovie.utils.base.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetMovieDetailUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository
) {

    suspend fun getMovieDetail(id: String) : Flow<State<DomainMovieDetail>> =
        resultFlow(
            databaseQuery = { localMoviesRepository.getMovie(id) },
            networkCall = { remoteMoviesRepository.getMovieDetail(id) },
            saveCallResult = { detail -> localMoviesRepository.insertMovieDetail(detail) }
        )
}