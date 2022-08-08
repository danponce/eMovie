package com.danponce.emovie.data.remote.model

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
data class MovieDetailData(
    val adult: Boolean? = false,
    val backdropPath: String = "",
    val budget: Int? = 0,
    val genres: List<MovieItemGenreData>? = listOf(),
    val homepage: String? = "",
    val id: Long? = 0,
    val imdbId: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val posterPath: String = "",
    val productionCompanies: List<MovieProductionCompanyData>? = listOf(),
    val productionCountries: List<MovieProductionCountryData>? = listOf(),
    val releaseDate: String,
    val revenue: Long? = 0,
    val runtime: Int? = 0,
    val spokenLanguages: List<MovieSpokenLanguageData>? = listOf(),
    val status: String? = "",
    val tagline: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
)
