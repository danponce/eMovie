package au.com.carsales.emovie.data.remote.mapper

import au.com.carsales.emovie.data.remote.model.MovieVideoItemData
import au.com.carsales.emovie.data.remote.model.MovieVideosResultData
import au.com.carsales.emovie.domain.model.DomainMovieVideoItem
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class RemoteToDomainMovieVideoItemMapper @Inject constructor() {
    fun executeMapping(videosItemData: MovieVideoItemData?) : DomainMovieVideoItem? {
        return videosItemData?.let {
            DomainMovieVideoItem(
                id = it.id ?: "",
                key = it.key ?: "",
                name = it.name ?: "",
                publishedAt = it.publishedAt ?: "",
                site = it.site ?: "",
                type = it.type ?: ""
            )
        }
    }
}