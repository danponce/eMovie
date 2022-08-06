package au.com.carsales.emovie.ui.mapper

import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.domain.utils.Mapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import javax.inject.Inject

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class UIMovieItemMapper @Inject constructor() : Mapper<UIMovieItem, DomainMovieItem> {

    override fun executeMapping(type: UIMovieItem?): DomainMovieItem {
        return type.let {
            DomainMovieItem(
                id = it?.id ?: 0,
                posterPath = it?.posterPath ?: "",
                originalTitle = it?.originalTitle ?: "",
                voteAverage = it?.voteAverage ?: 0.0,
                releaseDate = it?.releaseDate ?: "",
                originalLanguage = it?.originalLanguage ?: ""
            )
        }
    }
}