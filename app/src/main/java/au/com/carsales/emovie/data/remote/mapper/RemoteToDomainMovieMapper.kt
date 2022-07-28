package au.com.carsales.emovie.data.remote.mapper

import au.com.carsales.emovie.data.remote.model.MovieItemData
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteToDomainMovieMapper @Inject constructor() : Mapper<List<MovieItemData>?, List<DomainMovieItem>> {

    override fun executeMapping(type: List<MovieItemData>?): List<DomainMovieItem>? {
        return type?.map { movie ->
            DomainMovieItem(
                id = movie.id,
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }
}