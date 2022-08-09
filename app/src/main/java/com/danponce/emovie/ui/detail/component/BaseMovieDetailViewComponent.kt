package com.danponce.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewComponentDetailBaseBinding
import com.danponce.emovie.utils.base.viewcomponent.BaseDataBindingViewComponent

/**
 * Created by Dan on 02, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
abstract class BaseMovieDetailViewComponent <T : ViewDataBinding> : BaseDataBindingViewComponent<ViewComponentDetailBaseBinding>, ComponentTitle {

    abstract fun sectionTitle() : Int
    abstract fun childLayoutId() : Int

    lateinit var dataBinding : T

    private fun insertView(view : View) {
        binding.containerLayoutView.addView(view)
        binding.item = this
    }

    protected fun setBinding() {
        initBinding()

        dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), childLayoutId(), this, false)
        insertView(dataBinding.root)
    }

    override fun getTitle(): String = context.getString(sectionTitle())

    override fun layoutId(): Int = R.layout.view_component_detail_base

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBinding()
    }

    constructor(context: Context) : super(context) {
        setBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBinding()
    }
}