package com.danponce.emovie.domain.usecase

import com.danponce.emovie.domain.model.DomainMovieDetail
import com.danponce.emovie.domain.repository.LocalMoviesRepository
import com.danponce.emovie.domain.repository.RemoteMoviesRepository
import com.danponce.emovie.domain.utils.resultFlow
import com.danponce.emovie.utils.base.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
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