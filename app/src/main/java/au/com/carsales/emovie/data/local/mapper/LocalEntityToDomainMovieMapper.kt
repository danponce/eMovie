package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.BaseMovieEntity
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalEntityToDomainMovieMapper @Inject constructor() : Mapper<List<BaseMovieEntity>?, List<DomainMovieItem>?> {

    override fun executeMapping(type: List<BaseMovieEntity>?): List<DomainMovieItem>? {
        return type?.map { movie ->
            DomainMovieItem(
                id = movie.id?.toLong() ?: 0,
                posterPath = movie.posterPath ?:"",
                originalTitle = movie.originalTitle ?:"",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate ?:"",
                originalLanguage = movie.originalLanguage ?:""
            )
        }
    }
}