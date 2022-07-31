package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.EntityMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalEntityToDomainMovieDetailMapper @Inject constructor(
    private val videoItemMapper : LocalEntityToDomainMovieVideoMapper
) : Mapper<EntityMovieDetail, DomainMovieDetail> {
    override fun executeMapping(type: EntityMovieDetail?): DomainMovieDetail {
        return type!!.let {
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
                videos = it.videos.map { video -> videoItemMapper.executeMapping(video) }.filterNotNull()
            )
        }
    }
}