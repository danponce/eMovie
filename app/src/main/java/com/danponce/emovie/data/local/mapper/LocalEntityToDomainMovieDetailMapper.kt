package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.EntityMovieDetail
import com.danponce.emovie.domain.model.DomainMovieDetail
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class LocalEntityToDomainMovieDetailMapper @Inject constructor(
    private val videoItemMapper : LocalEntityToDomainMovieVideoMapper,
    private val movieItemListMapper : LocalEntityToDomainMovieMapper
) : Mapper<EntityMovieDetail, DomainMovieDetail> {
    override fun executeMapping(type: EntityMovieDetail?): DomainMovieDetail? {
        return type?.let {
            DomainMovieDetail(
                id = it.id.toInt(),
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                originalTitle = it.originalTitle,
                title = it.title,
                releaseDate = it.releaseDate,
                runtime = it.runtime,
                overview = it.overview,
                genres = it.genres,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                videos = it.videos.mapNotNull { video -> videoItemMapper.executeMapping(video) },
                similarMovies = movieItemListMapper.executeMapping(it.similarMovies).orEmpty()
            )
        }
    }
}