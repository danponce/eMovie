package com.danponce.emovie.ui.model

import android.os.Parcelable
import com.danponce.emovie.BuildConfig
import com.danponce.emovie.utils.DateUtils
import com.danponce.emovie.utils.LanguageHelper
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@Parcelize
class UIMovieItem(
    var id: Long = 0L,
    val posterPath: String = "",
    val originalTitle: String = "",
    val voteAverage: Double = 0.0,
    val releaseDate: String = "",
    val originalLanguage : String = ""
) : Parcelable {

    @IgnoredOnParcel
    val displayLanguage : String = LanguageHelper.getISOToLanguage(originalLanguage)

    @IgnoredOnParcel
    val releaseYear : String = DateUtils.getYearFromDate(releaseDate, DateUtils.yyyyMMddFormat)

    fun getFormattedPosterPath() = BuildConfig.IMAGE_URL + posterPath
}
