package com.danponce.emovie.utils.base.views

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */

fun Context.loadStyledAttributes(
    @StyleableRes styleableRes: IntArray,
    attrs: AttributeSet,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
    callback: (TypedArray) -> Unit,
) {
    this.theme.obtainStyledAttributes(
        attrs,
        styleableRes,
        defStyleAttr,
        defStyleRes).apply {
        try {
            callback(this)
        } finally {
            recycle()
        }
    }
}