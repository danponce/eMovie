package com.danponce.emovie.domain.usecase

import com.danponce.emovie.domain.repository.LocalMoviesRepository
import javax.inject.Inject

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetIsFavoriteMovieUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) {
    suspend fun isMovieFavorite(id: String) = localMoviesRepository.isFavorite(id)
}