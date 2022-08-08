package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.EntityFavoriteMovieItem
import com.danponce.emovie.data.local.model.EntityMovieItem
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 03, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityFavoriteMovieItemMapper @Inject constructor() :
    Mapper<DomainMovieItem?, EntityFavoriteMovieItem?> {

    override fun executeMapping(type: DomainMovieItem?): EntityFavoriteMovieItem? {
        return type?.let { movie ->
            EntityFavoriteMovieItem(
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