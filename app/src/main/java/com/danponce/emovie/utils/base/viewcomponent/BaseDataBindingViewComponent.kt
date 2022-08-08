package com.danponce.emovie.utils.base.viewcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Dan on 01, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseDataBindingViewComponent <T : ViewDataBinding> : RelativeLayout {

    abstract fun layoutId() : Int
    lateinit var binding: T

    fun initBinding() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId(), this, false)
        addView(binding.root)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBinding()
    }

    constructor(context: Context) : super(context) {
        initBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initBinding()
    }
}