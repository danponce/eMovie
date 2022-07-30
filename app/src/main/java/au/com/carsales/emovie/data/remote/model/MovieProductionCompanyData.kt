package au.com.carsales.emovie.data.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
data class MovieProductionCompanyData(
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("logo_path")
    val logoPath: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("origin_country")
    val originCountry: String? = ""
)
