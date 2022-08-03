package au.com.carsales.emovie.domain.model

/**
 * Created by Dan on 23, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class DomainMovieItem (
    var id: Long,
    val posterPath: String,
    val originalTitle: String,
    val voteAverage: Double,
    val releaseDate: String
) {
    fun hello() {}
}
