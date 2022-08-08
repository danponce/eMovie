package au.com.carsales.emovie.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentMovieDetailBinding
import au.com.carsales.emovie.ui.model.UIMovieDetail
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.TransitionConstants
import au.com.carsales.emovie.utils.base.setBackButton
import au.com.carsales.emovie.utils.getScreenHeight
import au.com.carsales.emovie.utils.getScreenHeightPart
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@AndroidEntryPoint
class MovieDetailFragment : BaseDataBindingFragment<FragmentMovieDetailBinding>() {

    override fun layoutId(): Int = R.layout.fragment_movie_detail

    private val detailViewModel: MovieDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        super.onCreateView(inflater, container, savedInstanceState)

        setObservers()

        setMovieImageHeight()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedElementTransitionOnEnter()
        postponeEnterTransition()

        if(detailViewModel.hasData()) {

            val lastMovie = detailViewModel.getLastMovieDetail()
            lastMovie?.let {
                setView(it)
            }

        } else {
            val args : MovieDetailFragmentArgs by navArgs()

            val movie = args.movie

            detailViewModel.setMovie(movie)
            detailViewModel.getMovieDetails()
            initViews(movie)
        }

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setBackButton(requireActivity()) { navigateBack() }

    }

    private fun initViews(movie: UIMovieItem) {
        binding.collapsingImageView.apply {

            transitionName = movie.id.toString()

            startEnterTransitionAfterLoadingImage(movie)
        }

        detailViewModel.isShowFavorite(movie.id.toString())
    }

    private fun startEnterTransitionAfterLoadingImage(movie: UIMovieItem) {
        Glide.with(this)
            .load(movie.getFormattedPosterPath())
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: com.bumptech.glide.request.target.Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(binding.collapsingImageView)
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    private fun setMovieImageHeight() {
        // Set movie image to take full screen height
        binding.collapsingImageView.layoutParams.height = getScreenHeightPart(3)
    }

    private fun setFabDrawable(isFavorite : Boolean) {
        val drawable = ContextCompat.getDrawable(
            requireContext(),
            if(isFavorite) {
                R.drawable.ic_baseline_favorite_24
            } else { R.drawable.ic_baseline_favorite_border_24 })

        binding.fab.setImageDrawable(drawable)
    }

    private fun setView(data: UIMovieDetail) {

        // Clean if any and then add the genre Chips
        binding.chipGroupView.removeAllViews()
        data.genres.forEach {
            val chip = Chip(requireContext())
            chip.text = it

            binding.chipGroupView.addView(chip)
        }

        binding.movieVideosViewComponent.setView(data)

        binding.fab.setOnClickListener {
            when(detailViewModel.isActualShowFavorite()) {
                true -> detailViewModel.deleteFavorite()
                false -> detailViewModel.addFavorite()
            }
        }

    }

    private fun setObservers() {
        detailViewModel.apply {

            isFavoriteLiveData.observe(viewLifecycleOwner) {
                setFabDrawable(it)
            }

            movieDetailsLiveData.observe(viewLifecycleOwner) {
                setView(it)
            }

        }
    }
}