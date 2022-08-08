package com.danponce.emovie.ui.mapper

import com.danponce.emovie.domain.model.DomainMovieDetail
import com.danponce.emovie.domain.utils.Mapper
import com.danponce.emovie.ui.model.UIMovieDetail
import javax.inject.Inject

/**
 * Created by Dan on 31, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class UIMovieDetailMapper @Inject constructor(
    private val videoMapper: UIMovieVideoMapper,
    private val movieItemListMapper : UIMovieItemListMapper
) : Mapper<DomainMovieDetail, UIMovieDetail> {
    override fun executeMapping(type: DomainMovieDetail?): UIMovieDetail? {
        return type?.let {
            UIMovieDetail(
                id = it.id,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                originalTitle = it.originalTitle,
                runtime = it.runtime,
                releaseDate = it.releaseDate,
                overview = it.overview,
                genres = it.genres,
                videos = it.videos.map { video -> videoMapper.executeMapping(video) },
                similarMovies = movieItemListMapper.executeMapping(it.similarMovies).orEmpty()
            )
        }
    }
}