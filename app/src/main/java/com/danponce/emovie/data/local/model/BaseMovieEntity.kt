package com.danponce.emovie.data.local.model

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
open class BaseMovieEntity(
    @Transient
    open val id: String? = null,
    @Transient
    open val posterPath: String? = null,
    @Transient
    open val originalTitle: String ?= null,
    @Transient
    open val voteAverage: Double ?= null,
    @Transient
    open val releaseDate: String? = null,
    @Transient
    open val originalLanguage: String?= null
)
