package au.com.carsales.emovie.domain.usecase

import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.repository.LocalMoviesRepository
import javax.inject.Inject

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class AddFavoriteMovieUseCase @Inject constructor(
    private val localMoviesRepository: LocalMoviesRepository
) {

    suspend fun addFavoriteMovie(movie : DomainMovieItem) {
        localMoviesRepository.addFavoriteMovie(movie)
    }
}