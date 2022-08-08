package com.danponce.emovie.data.remote.mapper

import com.danponce.emovie.data.remote.model.MovieItemData
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class RemoteToDomainMovieMapper @Inject constructor() : Mapper<List<MovieItemData>?, List<DomainMovieItem>> {

    override fun executeMapping(type: List<MovieItemData>?): List<DomainMovieItem>? {
        return type?.map { movie ->
            DomainMovieItem(
                id = movie.id,
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle,
                voteAverage = movie.voteAverage,
                releaseDate = movie.releaseDate,
                originalLanguage = movie.originalLanguage
            )
        }
    }
}