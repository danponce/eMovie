package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.BaseMovieEntity
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityMovieItemMapper @Inject constructor() : Mapper<DomainMovieItem?, BaseMovieEntity?> {

    override fun executeMapping(type: DomainMovieItem?): BaseMovieEntity? {
        return type?.let { movie ->
            BaseMovieEntity(
                id = movie.id.toString(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate
            )
        }
    }
}