package au.com.carsales.emovie.ui.model

import android.os.Parcelable
import au.com.carsales.emovie.BuildConfig
import au.com.carsales.emovie.utils.DateUtils
import au.com.carsales.emovie.utils.LanguageHelper
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
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
