package com.danponce.emovie.factory

import com.danponce.emovie.domain.model.DomainMovieDetail

/**
 * Created by Dan on 07, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
object MovieDetailFactory {
    fun createDomainMovieDetail() = DomainMovieDetail(
        id = 0,
        posterPath = "",
        backdropPath = "",
        originalTitle = "",
        releaseDate = "",
        overview = "",
        genres = arrayListOf(),
        voteAverage = 0.0,
        voteCount = 0,
        title = "",
        runtime = 0,
        videos = arrayListOf()
    )
}