package au.com.carsales.emovie.ui.model

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class UIMovieVideoItem(
    var id: Long = 0L,
    val posterPath: String = "",
    val originalTitle: String = "",
    val voteAverage: Double = 0.0,
    val releaseDate: String = "",
    var isFavorite: Boolean = false
)
