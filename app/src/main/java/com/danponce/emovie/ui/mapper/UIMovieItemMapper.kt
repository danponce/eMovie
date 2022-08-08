package com.danponce.emovie.ui.mapper

import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.domain.utils.Mapper
import com.danponce.emovie.ui.model.UIMovieItem
import javax.inject.Inject

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class UIMovieItemMapper @Inject constructor() : Mapper<UIMovieItem, DomainMovieItem> {

    override fun executeMapping(type: UIMovieItem?): DomainMovieItem {
        return type.let {
            DomainMovieItem(
                id = it?.id ?: 0,
                posterPath = it?.posterPath ?: "",
                originalTitle = it?.originalTitle ?: "",
                voteAverage = it?.voteAverage ?: Double.MIN_VALUE,
                releaseDate = it?.releaseDate ?: "",
                originalLanguage = it?.originalLanguage ?: ""
            )
        }
    }
}