package au.com.carsales.emovie.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentTvshowDetailBinding
import au.com.carsales.emovie.ui.detail.episode.EpisodeListFragment
import au.com.carsales.emovie.ui.detail.episode.EpisodesPagerAdapter
import au.com.carsales.emovie.ui.detail.episode.EpisodesSeason
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.LiveEvent
import au.com.carsales.emovie.utils.base.setBackButton
import au.com.carsales.emovie.utils.base.state.observeStateLiveData
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@AndroidEntryPoint
class MovieDetailFragment : BaseDataBindingFragment<FragmentTvshowDetailBinding>() {

    override fun layoutId(): Int = R.layout.fragment_tvshow_detail

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

    private fun setView(data: UIMovieItem) {
//        binding.toolbar.title = data.name

        // Add the genre Chips
//        data.genres?.forEach {
//            val chip = Chip(requireContext())
//            chip.text = it
//
//            binding.chipGroupView.addView(chip)
//        }

    }

    private fun setEpisodesTabLayout(episodesSeason: List<EpisodesSeason>) {
        binding.apply {
            val fragmentList = arrayListOf<EpisodeListFragment>()

            episodesSeason.forEach {
                fragmentList.add(EpisodeListFragment.newInstance(it))
            }

            val adapter =
                EpisodesPagerAdapter(
                    this@MovieDetailFragment,
                    fragmentList)
            viewPager.offscreenPageLimit = 1
            viewPager.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->

                tab.text = getString(R.string.episodes_season_tab_title, position + 1)

            }.attach()
        }
    }

    private fun setObservers() {
        detailViewModel.apply {

            isFavoriteLiveData.observe(viewLifecycleOwner) {
                setFabDrawable(it)
            }

        }
    }
}