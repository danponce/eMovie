package com.danponce.emovie.domain.usecase

import com.danponce.emovie.domain.repository.LocalMoviesRepository
import com.danponce.emovie.domain.utils.databaseResultFlow
import javax.inject.Inject

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class GetFavoriteMoviesUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) {
    suspend fun getFavoriteMovies() =
        databaseResultFlow { localMoviesRepository.getFavoriteMovies() }
}