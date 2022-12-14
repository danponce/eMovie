package com.danponce.emovie.utils

import androidx.fragment.app.Fragment

/**
 * Created by Dan on 29, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */

fun Fragment.getScreenHeight() = resources.displayMetrics.heightPixels
fun Fragment.getScreenWidth() = resources.displayMetrics.widthPixels
fun Fragment.getScreenWidthInDP() = ( getScreenWidth() / resources.displayMetrics.density )

fun Fragment.getScreenHeightPart(portion : Int) : Int {

    return when {
        portion > 0 -> getScreenHeight()/portion
        else -> 0
    }
}