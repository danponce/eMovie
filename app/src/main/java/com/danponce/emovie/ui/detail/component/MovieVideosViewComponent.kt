package com.danponce.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewComponentDetailVideoBinding
import com.danponce.emovie.ui.model.UIMovieDetail
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter

/**
 * Created by Dan on 02, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class MovieVideosViewComponent : BaseMovieDetailViewComponent {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBinding()
    }

    constructor(context: Context) : super(context) {
        initBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initBinding()
    }

    override fun sectionTitle(): Int = R.string.detail_video_section_title

    fun setView(detail : UIMovieDetail) {
        val binding = ViewComponentDetailVideoBinding.inflate(LayoutInflater.from(context), this, false)
        binding.itemDetail = detail

        binding.videosRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_video,
                detail.videos
            )
        }

        insertView(binding.root)
    }
}