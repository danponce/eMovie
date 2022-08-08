package com.danponce.emovie.domain.usecase

import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.repository.LocalMoviesRepository
import javax.inject.Inject

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class DeleteFavoriteMovieUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) {
    suspend fun deleteFavoriteMovie(movie: DomainMovieItem) {
        localMoviesRepository.deleteFavoriteMovie(movie)
    }
}