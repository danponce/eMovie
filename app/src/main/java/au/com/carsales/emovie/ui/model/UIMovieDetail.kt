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
    val runtime: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>,
    val videos: List<UIMovieVideoItem>,
    val credits: UiMovieCredits,
    val providers: List<UiMovieProviderItem>,
    val similar: List<UIMovieItem>,
    val translations: List<UiMovieTranslationItem>
)
