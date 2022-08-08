package com.danponce.emovie.domain.usecase

import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.repository.LocalMoviesRepository
import com.danponce.emovie.domain.repository.RemoteMoviesRepository
import com.danponce.emovie.domain.utils.resultFlow
import com.danponce.emovie.utils.base.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetTopRatedMoviesUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository,
    private val remoteMoviesRepository: RemoteMoviesRepository
) {

    suspend fun getTopRatedMovies() : Flow<State<List<DomainMovieItem>>> =
        resultFlow(
            databaseQuery = { localMoviesRepository.getTopRatedMovies() },
            networkCall = { remoteMoviesRepository.getTopRatedMovies() },
            saveCallResult = { movies -> localMoviesRepository.insertMovies(movies) }
        )

}