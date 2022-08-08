package com.danponce.emovie.domain.repository

import com.danponce.emovie.domain.DomainMovieDataState
import com.danponce.emovie.domain.DomainMovieDetailDataState
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 23, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface RemoteMoviesRepository {

    suspend fun getUpcomingMovies() : Flow<DomainMovieDataState>
    suspend fun getTopRatedMovies() : Flow<DomainMovieDataState>
    suspend fun getMovieDetail(movieId: String) : Flow<DomainMovieDetailDataState>

}