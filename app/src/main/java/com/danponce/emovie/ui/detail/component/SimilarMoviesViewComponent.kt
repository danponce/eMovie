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
class SimilarMoviesViewComponent : BaseMovieDetailViewComponent<ViewComponentDetailSimilarMoviesBinding> {

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBinding()
    }

    constructor(context: Context) : super(context) {
        setBinding()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBinding()
    }

    override fun childLayoutId(): Int = R.layout.view_component_detail_similar_movies

    override fun sectionTitle(): Int = R.string.detail_similar_movies_section_title

    fun setView(detail : UIMovieDetail, clickListener : (UIMovieItem) -> Unit) {

        when(dataBinding.similarMoviesRecyclerView.adapter) {
            null -> dataBinding.similarMoviesRecyclerView.apply {
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = SingleLayoutBindRecyclerAdapter(
                            R.layout.view_cell_similar_movies,
                            detail.similarMovies,
                            clickHandler = { _, movie ->
                                clickListener.invoke(movie)
                            }
                        )
                    }

            else -> (dataBinding.similarMoviesRecyclerView.adapter as SingleLayoutBindRecyclerAdapter<UIMovieItem>).setData(detail.similarMovies)
        }
        
    }

}