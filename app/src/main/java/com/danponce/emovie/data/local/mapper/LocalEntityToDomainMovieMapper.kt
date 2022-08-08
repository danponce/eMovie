package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.BaseMovieEntity
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class LocalEntityToDomainMovieMapper @Inject constructor() : Mapper<List<BaseMovieEntity>?, List<DomainMovieItem>?> {

    override fun executeMapping(type: List<BaseMovieEntity>?): List<DomainMovieItem>? {
        return type?.map { movie ->
            DomainMovieItem(
                id = movie.id?.toLong() ?: 0,
                posterPath = movie.posterPath ?:"",
                originalTitle = movie.originalTitle ?:"",
                voteAverage = movie.voteAverage ?: Double.MIN_VALUE,
                releaseDate = movie.releaseDate ?:"",
                originalLanguage = movie.originalLanguage ?:""
            )
        }
    }
}