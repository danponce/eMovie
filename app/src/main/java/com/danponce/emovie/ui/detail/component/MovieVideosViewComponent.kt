package com.danponce.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewComponentDetailVideoBinding
import com.danponce.emovie.ui.model.UIMovieDetail
import com.danponce.emovie.ui.model.UIMovieVideoItem
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController

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

    private var youTubePlayer: YouTubePlayer ?= null

    override fun sectionTitle(): Int = R.string.detail_video_section_title

    fun setView(detail : UIMovieDetail, lifecycle: Lifecycle) {
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

        setYoutubePlayer(detail, lifecycle)
    }

    private fun setYoutubePlayer(detail : UIMovieDetail, lifecycle: Lifecycle) {

        lifecycle.addObserver(dataBinding.youtubePlayerView)

        dataBinding.youtubePlayerView.apply {
            enableAutomaticInitialization = false
        }

        val listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {

                val defaultPlayerUiController = DefaultPlayerUiController(dataBinding.youtubePlayerView, youTubePlayer)
                dataBinding.youtubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)

                this@MovieVideosViewComponent.youTubePlayer = youTubePlayer

                this@MovieVideosViewComponent.youTubePlayer?.loadOrCueVideo(
                    lifecycle,
                    detail.videos.first().key,
                    0f
                )
            }
        }

        dataBinding.youtubePlayerView.addYouTubePlayerListener(listener)

        // disable web ui
//        val options: IFramePlayerOptions = IFramePlayerOptions.Builder().controls(0).build()
//        dataBinding.youtubePlayerView.initialize(listener, options)
//        movieBinding.youtubePlayerView.setOnClickListener {
////            this@MovieVideosViewComponent.youTubePlayer?.play()
//        }
    }


}