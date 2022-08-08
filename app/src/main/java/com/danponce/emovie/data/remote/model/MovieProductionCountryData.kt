package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
data class MovieProductionCountryData(
    @SerializedName("iso_3166_1")
    val iso31661: String? = "",
    @SerializedName("name")
    val name: String? = ""
)
