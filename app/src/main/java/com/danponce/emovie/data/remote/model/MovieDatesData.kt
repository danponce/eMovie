package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
data class MovieDatesData(
    @SerializedName("maximum")
    var maximum: String,

    @SerializedName("minimum")
    var minimum: String,
)
