package au.com.carsales.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.View
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.ViewComponentDetailBaseBinding
import au.com.carsales.emovie.utils.base.viewcomponent.BaseDataBindingViewComponent

/**
 * Created by Dan on 02, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseMovieDetailViewComponent : BaseDataBindingViewComponent<ViewComponentDetailBaseBinding>, ComponentTitle {

    abstract fun sectionTitle() : Int
    fun insertView(view : View) {
        binding.containerLayoutView.addView(view)
        binding.item = this
    }

    override fun getTitle(): String = context.getString(sectionTitle())

    override fun layoutId(): Int = R.layout.view_component_detail_base

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