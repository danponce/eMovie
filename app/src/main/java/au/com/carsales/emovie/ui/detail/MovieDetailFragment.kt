package au.com.carsales.emovie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentMovieDetailBinding
import au.com.carsales.emovie.ui.model.UIMovieDetail
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.setBackButton
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args : MovieDetailFragmentArgs by navArgs()

        val movie = args.movie

        detailViewModel.setMovie(movie)
        detailViewModel.getMovieDetails()

//        setView(movie)

//        val viewModelData = detailViewModel.getExistentEpisodesData()

//        if(viewModelData != null) {
////            setEpisodesTabLayout(detailViewModel.getEpisodesSeasons())
//            val lastShow = detailViewModel.getLastShow()
//
//            // Set again views with last show from API
//            lastShow?.let {
//                setView(it)
//            }
//
//        } else {
//            val args : MovieDetailFragmentArgs by navArgs()
//
//            val movie = args.movie
//
//            detailViewModel.setMovie(movie)
//            detailViewModel.getEpisodes()
//
//            setView(movie)
//        }

        detailViewModel.isShowFavorite()

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.toolbar.setBackButton(requireActivity()) { navigateBack() }

        initListeners()
    }

    private fun initListeners() {

        binding.fab.setOnClickListener {
            when(detailViewModel.isActualShowFavorite()) {
                true -> detailViewModel.deleteFavorite()
                false -> detailViewModel.addFavorite()
            }
        }

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

        // Add the genre Chips
        data.genres.forEach {
            val chip = Chip(requireContext())
            chip.text = it

            binding.chipGroupView.addView(chip)
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