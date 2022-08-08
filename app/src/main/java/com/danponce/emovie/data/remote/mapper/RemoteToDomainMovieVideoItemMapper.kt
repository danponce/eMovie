package com.danponce.emovie.data.remote.mapper

import com.danponce.emovie.data.remote.model.MovieVideoItemData
import com.danponce.emovie.data.remote.model.MovieVideosResultData
import com.danponce.emovie.domain.model.DomainMovieVideoItem
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