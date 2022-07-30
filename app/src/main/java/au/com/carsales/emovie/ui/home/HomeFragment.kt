package au.com.carsales.emovie.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentHomeBinding
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.emovie.utils.base.getToolbarHeight
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

    private fun setTopRatedRecyclerView(data : List<UIMovieItem>?) {
        setMoviesRecyclerView(binding.topRatedMoviesRecyclerView, data)
    }

    private fun setUpcomingRecyclerView(data : List<UIMovieItem>?) {
        setMoviesRecyclerView(binding.upcomingMoviesRecyclerView, data)
    }

    private fun setRecommendedRecyclerView(data : List<UIMovieItem>?) {

        val layoutManager = GridLayoutManager(requireContext(), 2)

        setMoviesRecyclerView(binding.recommendedMoviesRecyclerView, data, layoutManager, R.layout.view_cell_movie_grid)
    }

    private fun setMoviesRecyclerView(
        recyclerView: RecyclerView,
        data : List<UIMovieItem>?,
        recyclerLayoutManager : RecyclerView.LayoutManager? = null,
        viewId : Int? = null
    ) {
        recyclerView.apply {
            layoutManager = recyclerLayoutManager ?: LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = SingleLayoutBindRecyclerAdapter (
                viewId ?: R.layout.view_cell_movie,
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

        homeViewModel.apply {
            getUpcomingMovies()
            getTopRatedMovies()
        }

        return binding.root
    }

    private fun setObservers() {

        homeViewModel.apply {
            upcomingMoviesLiveData.observe(viewLifecycleOwner) {
                setUpcomingRecyclerView(it)
            }

            topRatedMoviesLiveData.observe(viewLifecycleOwner) {
                setTopRatedRecyclerView(it)
                setRecommendedRecyclerView(it)
            }
        }

    }

    private fun setDataToRecyclerView(data : List<UIMovieItem>) {
        val adapter = binding.upcomingMoviesRecyclerView.adapter

        when(adapter) {
            null -> setUpcomingRecyclerView(data)
            else -> (adapter as SingleLayoutBindRecyclerAdapter<UIMovieItem>).setData(data)
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
            homeViewModel.getUpcomingMovies()
        }
    }

}