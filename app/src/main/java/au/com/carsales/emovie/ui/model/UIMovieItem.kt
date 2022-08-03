package au.com.carsales.emovie.ui.model

import android.os.Parcelable
import au.com.carsales.emovie.BuildConfig
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 26, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
data class UIMovieItem(
    var id: Long = 0L,
    val posterPath: String = "",
    val originalTitle: String = "",
    val voteAverage: Double = 0.0,
    val releaseDate: String = ""
) : Parcelable {

    fun getFormattedPosterPath() = BuildConfig.IMAGE_URL + posterPath
}
