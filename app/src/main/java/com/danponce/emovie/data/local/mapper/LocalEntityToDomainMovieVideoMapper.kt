package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.EntityMovieVideo
import com.danponce.emovie.domain.model.DomainMovieVideoItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalEntityToDomainMovieVideoMapper @Inject constructor() : Mapper<EntityMovieVideo, DomainMovieVideoItem> {
    override fun executeMapping(type: EntityMovieVideo?): DomainMovieVideoItem? {
        return type?.let {
            DomainMovieVideoItem(
                id = it.id,
                key = it.key,
                name = it.name,
                publishedAt = it.publishedAt,
                site = it.site,
                type = it.type
            )
        }
    }
}
