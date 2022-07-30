package au.com.carsales.emovie.ui.model

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class UIMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val runtime: Int,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>,
    val videos: List<UIMovieVideoItem>,
    val similar: List<UIMovieItem>,
) {
     fun calculateTime(): String {
        val hours = runtime / 60
        val minutes = runtime % 60
        return String.format("%d h %02d m", hours, minutes)
    }
}
