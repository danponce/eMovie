package com.danponce.emovie.factory

import com.danponce.emovie.domain.model.DomainMovieItem

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
object MovieListFactory {

    fun createDomainMoviesList() = (0..5).map {
        DomainMovieItem(
            id = 1L,
            posterPath = "",
            originalTitle = "",
            voteAverage = 0.0,
            releaseDate = "",
            originalLanguage = ""
        )
    }

    fun emptyMoviesList() = listOf<DomainMovieItem>()
}