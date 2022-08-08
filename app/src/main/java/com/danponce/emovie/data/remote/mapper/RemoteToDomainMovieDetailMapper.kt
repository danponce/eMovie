package com.danponce.emovie.data.remote.mapper

import com.danponce.emovie.data.remote.model.MovieData
import com.danponce.emovie.data.remote.model.MovieDetailData
import com.danponce.emovie.data.remote.model.MovieVideosResultData
import com.danponce.emovie.domain.model.DomainMovieDetail
import javax.inject.Inject

/**
 * Created by Dan on 28, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class RemoteToDomainMovieDetailMapper @Inject constructor(
    private val videoItemMapper: RemoteToDomainMovieVideoItemMapper,
    private val movieItemListMapper : RemoteToDomainMovieMapper
) {

    fun executeMapping(type: MovieDetailData?, videosResultData: MovieVideosResultData?, similarMoviesData: MovieData?): DomainMovieDetail {
        return type.let {
            DomainMovieDetail(
                id = it?.id?.toInt() ?: 0,
                posterPath = it?.posterPath ?: "",
                backdropPath = it?.backdropPath ?: "",
                originalTitle = it?.originalTitle ?: "",
                title = it?.title ?: "",
                runtime = it?.runtime ?: 0,
                releaseDate = it?.releaseDate ?: "",
                overview = it?.overview ?: "",
                genres = it?.genres?.map { it.name ?: "" } ?: listOf(),
                voteAverage = it?.voteAverage ?: Double.MIN_VALUE,
                voteCount = it?.voteCount ?: 0,
                videos = videosResultData?.results?.mapNotNull { item -> videoItemMapper.executeMapping(item) } ?: listOf(),
                similarMovies = movieItemListMapper.executeMapping(similarMoviesData?.results).orEmpty()
            )
        }
    }
}