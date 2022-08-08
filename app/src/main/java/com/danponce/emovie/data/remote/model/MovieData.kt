package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
data class MovieData(
    @SerializedName("dates")
    var dates: MovieDatesData,

    @SerializedName("page")
    var page: Int,

    @SerializedName("results")
    var results: List<MovieItemData>,
)
