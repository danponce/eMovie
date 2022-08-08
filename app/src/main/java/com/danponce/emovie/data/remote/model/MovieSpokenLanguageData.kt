package com.danponce.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class MovieSpokenLanguageData(
    @SerializedName("english_name")
    val englishName: String? = "",
    @SerializedName("iso_3166_1")
    val iso6391: String? = "",
    @SerializedName("name")
    val name: String? = ""
)
