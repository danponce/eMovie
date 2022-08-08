package com.danponce.emovie.ui.mapper

import com.danponce.emovie.domain.model.DomainMovieVideoItem
import com.danponce.emovie.domain.utils.Mapper
import com.danponce.emovie.ui.model.UIMovieVideoItem
import com.danponce.emovie.utils.YouTubeUtils
import javax.inject.Inject

/**
 * Created by Dan on 31, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class UIMovieVideoMapper @Inject constructor() : Mapper<DomainMovieVideoItem,UIMovieVideoItem> {
    override fun executeMapping(type: DomainMovieVideoItem?): UIMovieVideoItem {
        return type.let {
            UIMovieVideoItem(
                id = it?.id ?: "",
                key = it?.key ?: "",
                name = it?.name ?: "",
                publishedAt = it?.publishedAt ?:"",
                site = it?.site ?:"",
                type = it?.type ?:"",
                thumbnail = YouTubeUtils.getYouTubeThumbnail(it?.key ?: "")
            )
        }
    }
}