package au.com.carsales.emovie.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentHomeBinding
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.emovie.utils.base.getToolbarHeight
import au.com.carsales.emovie.utils.getScreenHeightPart
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 *
 * Shows a list of movies
 */
@AndroidEntryPoint
class HomeFragment : BaseDataBindingFragment<FragmentHomeBinding>() {

    override fun layoutId(): Int = R.layout.fragment_home

    private val homeViewModel : HomeViewModel by viewModels()

    private fun setDataToRecyclerView(data : List<UIMovieItem>) {
        val adapter = binding.upcomingMoviesRecyclerView.adapter

        when(adapter) {
            null -> setCommonRecyclerView(data)
            else -> (adapter as SingleLayoutBindRecyclerAdapter<UIMovieItem>).setData(data)
        }
    }

    private fun setCommonRecyclerView(data : List<UIMovieItem>?) {

        binding.upcomingMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleLayoutBindRecyclerAdapter (
                R.layout.view_cell_movie,
                data,
                clickHandler = { _, item ->
                    goToDetailsScreen(item)
                })

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        binding.topRatedMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleLayoutBindRecyclerAdapter (
                R.layout.view_cell_movie,
                data,
                clickHandler = { _, item ->
                    goToDetailsScreen(item)
                })

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }

        binding.recommendedMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleLayoutBindRecyclerAdapter (
                R.layout.view_cell_movie,
                data,
                clickHandler = { _, item ->
                    goToDetailsScreen(item)
                })

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewModel = homeViewModel

        setObservers()

        homeViewModel.getMovies()

        return binding.root
    }

    private fun setObservers() {
        homeViewModel.moviesLiveData.observe(viewLifecycleOwner) {
            setCommonRecyclerView(it)
        }
    }

    private fun goToDetailsScreen(data: UIMovieItem) {
        val direction = HomeFragmentDirections.goToDetailsAction(data)
        navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {

        // Let swipe refresh start just below toolbar
        val toolbarHeight = getToolbarHeight() ?: 50

        val endOffset = binding.swipeRefreshView.progressViewEndOffset
        binding.swipeRefreshView.setProgressViewOffset(false, toolbarHeight, endOffset + toolbarHeight)
        binding.swipeRefreshView.setOnRefreshListener {
            homeViewModel.getMovies()
        }

        binding.favoriteToolbarImageView.setOnClickListener {

        }

//        val thirdScreenHeight = getScreenHeightPart(3)
//
//        val params: ViewGroup.LayoutParams = binding.moviesRecyclerView.layoutParams
//        params.height = thirdScreenHeight
//        binding.moviesRecyclerView.layoutParams = params

    }

}