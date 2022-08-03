package au.com.carsales.emovie.ui.home

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding.viewModel = homeViewModel

        setObservers()

        return binding.root
    }

    private fun setObservers() {

        homeViewModel.apply {
            upcomingMoviesLiveData.observe(viewLifecycleOwner) {
                setDataToRecyclerView(it, binding.upcomingMoviesRecyclerView)
            }

            topRatedMoviesLiveData.observe(viewLifecycleOwner) {
                setDataToRecyclerView(it, binding.topRatedMoviesRecyclerView)
                setDataToRecyclerView(it, binding.recommendedMoviesRecyclerView)
            }
        }

    }

    private fun setDataToRecyclerView(data : List<UIMovieItem>, recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter

        when(adapter) {
            null -> {
                when(recyclerView) {
                    binding.upcomingMoviesRecyclerView -> setUpcomingRecyclerView(data)
                    binding.topRatedMoviesRecyclerView -> setTopRatedRecyclerView(data)
                    binding.recommendedMoviesRecyclerView -> setRecommendedRecyclerView(data)
                }
            }
            else -> (adapter as SingleLayoutBindRecyclerAdapter<UIMovieItem>).setData(data)
        }
    }

    private fun goToDetailsScreen(data: UIMovieItem, extras: FragmentNavigator.Extras) {
        val direction = HomeFragmentDirections.goToDetailsAction(data)
        navigate(direction, extras)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        if(homeViewModel.hasData()) {
            homeViewModel.apply {
                setDataToRecyclerView(getLastUpcomingData(), binding.upcomingMoviesRecyclerView)
                setDataToRecyclerView(getLastTopRatedData(), binding.topRatedMoviesRecyclerView)
                setDataToRecyclerView(getLastTopRatedData(), binding.recommendedMoviesRecyclerView)
            }

        } else {
            homeViewModel.apply {
                getUpcomingMovies()
                getTopRatedMovies()
            }
        }

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
                clickHandler = { view, item ->
                    val movieImageView = view.findViewById<ImageView>(R.id.movieImageView)
                    movieImageView.transitionName = item.id.toString()
                    val extras = FragmentNavigatorExtras(
                        movieImageView to item.id.toString()
                    )
                    goToDetailsScreen(item, extras)
                })

            // Allows recycler view state restoration
            adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
    }

}