package au.com.carsales.emovie.ui.mapper

import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.ui.model.UIMovieItem

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class UIMovieMapper {

    fun DomainMovieItem.fromDomainToUi() = UIMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )
}