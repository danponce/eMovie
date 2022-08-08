package com.danponce.emovie.ui.detail.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.danponce.emovie.R
import com.danponce.emovie.databinding.ViewComponentDetailSimilarMoviesBinding
import com.danponce.emovie.ui.model.UIMovieDetail
import com.danponce.emovie.ui.model.UIMovieItem
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter

/**
 * Created by Dan on 08, agosto, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class SimilarMoviesViewComponent : BaseMovieDetailViewComponent {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initBinding()
    }

    constructor(context: Context) : super(context) {
        initBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initBinding()
    }

    override fun sectionTitle(): Int = R.string.detail_similar_movies_section_title

    private var recyclerSet = false

    fun setView(detail : UIMovieDetail) {
        val binding = ViewComponentDetailSimilarMoviesBinding.inflate(LayoutInflater.from(context), this, false)

        if(!recyclerSet) {
            binding.similarMoviesRecyclerView.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = SingleLayoutBindRecyclerAdapter(
                    R.layout.view_cell_similar_movies,
                    detail.similarMovies
                )

                recyclerSet = true
            }
        }

        insertView(binding.root)
    }

}