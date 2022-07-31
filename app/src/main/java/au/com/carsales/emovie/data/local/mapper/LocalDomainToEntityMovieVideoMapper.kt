package au.com.carsales.emovie.data.local.mapper

import au.com.carsales.emovie.data.local.model.EntityMovieVideo
import au.com.carsales.emovie.domain.model.DomainMovieVideoItem
import au.com.carsales.emovie.domain.utils.Mapper
import javax.inject.Inject

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class LocalDomainToEntityMovieVideoMapper @Inject constructor() : Mapper<DomainMovieVideoItem, EntityMovieVideo> {
    override fun executeMapping(type: DomainMovieVideoItem?): EntityMovieVideo? {
        return type?.let {
            EntityMovieVideo(
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
