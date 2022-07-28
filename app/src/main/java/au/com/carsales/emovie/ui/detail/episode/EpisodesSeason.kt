package au.com.carsales.emovie.ui.detail.episode

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
data class EpisodesSeason (
    val season : Int
    ) : Parcelable