package au.com.carsales.emovie.data.local.model

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
open class BaseMovieEntity(
    open val id: String? = null,
    open val posterPath: String? = null,
    open val originalTitle: String ?= null,
    open val voteAverage: Double ?= null,
    open val releaseDate: String? = null,
    open val originalLanguage: String?= null
)
