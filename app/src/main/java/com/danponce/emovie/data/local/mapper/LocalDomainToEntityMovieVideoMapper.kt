package com.danponce.emovie.data.local.mapper

import com.danponce.emovie.data.local.model.EntityMovieVideo
import com.danponce.emovie.domain.model.DomainMovieVideoItem
import com.danponce.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityMovieVideoMapper @Inject constructor() : Mapper<DomainMovieVideoItem, EntityMovieVideo> {
    override fun executeMapping(type: DomainMovieVideoItem?): EntityMovieVideo? {
        return type?.let {
            EntityMovieVideo(
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
