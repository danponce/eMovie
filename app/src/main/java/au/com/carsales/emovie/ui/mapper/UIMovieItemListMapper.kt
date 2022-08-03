package au.com.carsales.emovie.ui.mapper

import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import javax.inject.Inject

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class UIMovieItemListMapper @Inject constructor() : Mapper<List<DomainMovieItem>, List<UIMovieItem>> {

    override fun executeMapping(type: List<DomainMovieItem>?): List<UIMovieItem>? {
        return type?.map {
            UIMovieItem(
                id = it.id,
                posterPath = it.posterPath,
                originalTitle = it.originalTitle,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate
            )
        }
    }
}