package au.com.carsales.emovie.data.remote.mapper

import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.data.remote.model.MovieItemData
import au.com.carsales.emovie.domain.model.DomainMovieItem
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
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }

    fun fromRemoteToEntity(
        results: List<MovieItemData>?,
    ): List<EntityMovieItem> {
        return results!!.map { movie ->
            EntityMovieItem(
                id = movie.id.toString(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }

    fun fromEntityToDomain(
        results: List<EntityMovieItem>?,
    ): List<DomainMovieItem> {
        return results!!.map { movie ->
            DomainMovieItem(
                id = movie.id.toLong(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }

    fun fromDomainToEntity(
        results: List<DomainMovieItem>?,
    ): List<EntityMovieItem> {
        return results!!.map { movie ->
            EntityMovieItem(
                id = movie.id.toString(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }

    fun MovieItemData.fromRemoteToDomain() = DomainMovieItem(
        id = id ?: 0,
        posterPath = posterPath,
        originalTitle = originalTitle ?: "",
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate,
        isFavorite = false
    )

    fun EntityMovieItem.fromEntityToDomain() = DomainMovieItem(
        id = id.toLong(),
        posterPath = posterPath,
        originalTitle = originalTitle ?: "",
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate,
        isFavorite = false
    )

}