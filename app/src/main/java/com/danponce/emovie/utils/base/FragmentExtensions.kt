package com.danponce.emovie.utils.base

import android.app.Activity
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
fun Toolbar.setBackButton(context: Activity, clickAction : () -> Unit) {
    if (context is AppCompatActivity) {
        context.setSupportActionBar(this)
        context.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    this.setNavigationOnClickListener { clickAction.invoke() }
}

fun Fragment.getToolbarHeight() : Int? {
    val tv = TypedValue()
    if (requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {

        return TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
    }

    return null
}