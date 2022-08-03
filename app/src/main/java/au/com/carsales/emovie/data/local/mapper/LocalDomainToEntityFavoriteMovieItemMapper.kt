package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.EntityFavoriteMovieItem
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
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
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate
            )
        }
    }
}