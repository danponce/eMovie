package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 24, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class MovieItemData(

    @SerializedName("id")
    var id: Long,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("release_date")
    val releaseDate: String

)