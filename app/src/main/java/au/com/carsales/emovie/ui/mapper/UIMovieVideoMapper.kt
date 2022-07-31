package au.com.carsales.emovie.ui.mapper

import au.com.carsales.emovie.domain.model.DomainMovieVideoItem
import au.com.carsales.emovie.domain.utils.Mapper
import au.com.carsales.emovie.ui.model.UIMovieVideoItem
import javax.inject.Inject

/**
 * Created by Dan on 31, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
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
                type = it?.type ?:""
            )
        }
    }
}