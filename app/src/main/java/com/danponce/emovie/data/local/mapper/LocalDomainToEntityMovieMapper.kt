package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.EntityMovieItem
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityMovieMapper @Inject constructor() : Mapper<List<DomainMovieItem>?, List<EntityMovieItem>?> {

    override fun executeMapping(type: List<DomainMovieItem>?): List<EntityMovieItem>? {
        return type?.map { movie ->
            EntityMovieItem(
                id = movie.id.toString(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle,
                voteAverage = movie.voteAverage,
                releaseDate = movie.releaseDate,
                originalLanguage = movie.originalLanguage
            )
        }
    }
}