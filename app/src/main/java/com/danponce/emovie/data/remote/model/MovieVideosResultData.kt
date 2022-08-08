package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class MovieVideosResultData(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("results")
    val results: List<MovieVideoItemData>? = listOf()
)
