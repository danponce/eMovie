package com.danponce.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewComponentDetailVideoBinding
import com.danponce.emovie.ui.model.UIMovieDetail
import com.danponce.emovie.ui.model.UIMovieItem
import com.danponce.emovie.ui.model.UIMovieVideoItem
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

/**
 * Created by Dan on 02, agosto, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class MovieVideosViewComponent : BaseMovieDetailViewComponent<ViewComponentDetailVideoBinding> {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBinding()
    }

    constructor(context: Context) : super(context) {
        setBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBinding()
    }

    override fun childLayoutId(): Int = R.layout.view_component_detail_video

    override fun sectionTitle(): Int = R.string.detail_video_section_title

    fun setView(detail : UIMovieDetail) {
        dataBinding.itemDetail = detail

        when(dataBinding.videosRecyclerView.adapter) {
            null -> dataBinding.videosRecyclerView.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = SingleLayoutBindRecyclerAdapter(
                            R.layout.view_cell_video,
                            detail.videos
                        )
                    }

            else -> (dataBinding.videosRecyclerView.adapter as SingleLayoutBindRecyclerAdapter<UIMovieVideoItem>).setData(detail.videos)
        }
    }
}