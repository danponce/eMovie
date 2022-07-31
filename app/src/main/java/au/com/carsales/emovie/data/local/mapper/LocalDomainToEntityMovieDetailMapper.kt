package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.EntityMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieVideoItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityMovieDetailMapper @Inject constructor(
    private val videoItemMapper : LocalDomainToEntityMovieVideoMapper
) : Mapper<DomainMovieDetail, EntityMovieDetail> {
    override fun executeMapping(type: DomainMovieDetail?): EntityMovieDetail? {
        return type?.let {
            EntityMovieDetail(
                id = it.id.toString(),
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
                videos = it.videos.mapNotNull { video -> videoItemMapper.executeMapping(video) }
            )
        }
    }
}