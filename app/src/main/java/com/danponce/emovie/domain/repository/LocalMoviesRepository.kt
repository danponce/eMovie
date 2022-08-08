package com.danponce.emovie.domain.repository

import com.danponce.emovie.domain.DomainMovieDataState
import com.danponce.emovie.domain.model.DomainMovieDetail
import com.danponce.emovie.domain.model.DomainMovieItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 23, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface LocalMoviesRepository {

    suspend fun getUpcomingMovies() : Flow<List<DomainMovieItem>>
    suspend fun getTopRatedMovies() : Flow<List<DomainMovieItem>>
    suspend fun getAllMovies() : Flow<List<DomainMovieItem>>
    suspend fun insertMovies(movieList : List<DomainMovieItem>)
    suspend fun getMovie(id: String) : Flow<DomainMovieDetail?>
    suspend fun insertMovieDetail(movieDetail: DomainMovieDetail)
    suspend fun getFavoriteMovies() : Flow<List<DomainMovieItem>>
    suspend fun isFavorite(id: String) : Flow<Boolean>
    suspend fun addFavoriteMovie(movie: DomainMovieItem)
    suspend fun deleteFavoriteMovie(movie: DomainMovieItem)
}