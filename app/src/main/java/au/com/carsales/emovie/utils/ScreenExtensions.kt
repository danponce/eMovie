package au.com.carsales.emovie.utils

import androidx.fragment.app.Fragment

/**
 * Created by Dan on 29, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

fun Fragment.getScreenHeight() = resources.displayMetrics.heightPixels

fun Fragment.getScreenHeightPart(portion : Int) : Int {

    return when {
        portion > 0 -> getScreenHeight()/portion
        else -> 0
    }
}