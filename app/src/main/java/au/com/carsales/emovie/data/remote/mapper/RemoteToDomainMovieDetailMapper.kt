package au.com.carsales.emovie.data.remote.mapper

import au.com.carsales.emovie.data.remote.model.MovieDetailData
import au.com.carsales.emovie.data.remote.model.MovieItemData
import au.com.carsales.emovie.data.remote.model.MovieVideosResultData
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteToDomainMovieDetailMapper @Inject constructor(
    private val videoItemMapper: RemoteToDomainMovieVideoItemMapper
) {

    fun executeMapping(type: MovieDetailData?, videosResultData: MovieVideosResultData?): DomainMovieDetail {
        return type.let {
            DomainMovieDetail(
                id = it?.id?.toInt() ?: 0,
                posterPath = it?.posterPath ?: "",
                backdropPath = it?.backdropPath ?: "",
                originalTitle = it?.originalTitle ?: "",
                runtime = it?.runtime ?: 0,
                releaseDate = it?.releaseDate ?: "",
                overview = it?.overview ?: "",
                genres = it?.genres?.map { it.name ?: "" } ?: listOf(),
                videos = videosResultData?.results?.mapNotNull { item -> videoItemMapper.executeMapping(item) } ?: listOf()
            )
        }
    }
}