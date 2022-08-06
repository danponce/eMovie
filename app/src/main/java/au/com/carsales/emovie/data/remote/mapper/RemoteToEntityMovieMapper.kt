package au.com.carsales.emovie.data.remote.mapper

import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.data.remote.model.MovieItemData
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteToEntityMovieMapper @Inject constructor() : Mapper<List<MovieItemData>?,List<EntityMovieItem>> {

    override fun executeMapping(type: List<MovieItemData>?): List<EntityMovieItem>? {
        return type!!.map { movie ->
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