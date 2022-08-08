package com.danponce.emovie.ui.home

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.danponce.emovie.R
import com.danponce.emovie.databinding.FragmentHomeBinding
import com.danponce.emovie.ui.model.UIMovieItem
import com.danponce.emovie.utils.MovieDBConstants
import com.danponce.emovie.utils.base.BaseDataBindingFragment
import com.danponce.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import com.danponce.emovie.utils.base.views.BottomSheetDialogListListener
import com.danponce.emovie.utils.getScreenWidth
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022. All rights reserved.
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
        binding.lifecycleOwner = viewLifecycleOwner

        setObservers()

        setRecommendedRecyclerViewHeight()

        return binding.root
    }

    private fun setRecommendedRecyclerViewHeight() {
        val screenPadding = resources.getDimension(R.dimen.default_view_padding)

        // Minus left and right paddings
        val screenWidthMinusPadding = getScreenWidth() - (screenPadding * 2)
        val splitWidth = (screenWidthMinusPadding / 2).toInt()

        // Following aspect ratio
        val rowHeight = (splitWidth * MovieDBConstants.ASPECT_RATIO_HEIGHT) / MovieDBConstants.ASPECT_RATIO_WIDTH

        val itemsByRow = resources.getInteger(R.integer.home_recommended_grid_span)
        val maxItems = resources.getInteger(R.integer.home_recommended_grid_max_items)
        var recyclerViewHeight = rowHeight * (maxItems / itemsByRow)

        // To improve ui we'll remove toolbar height
        val toolbarHeight = binding.appBar.measuredHeight
        recyclerViewHeight -= toolbarHeight

        // We set the height of the recycler view
        // according to the height calculated rows
        // related with max items to be showed in grid
        binding.recommendedMoviesRecyclerView.layoutParams.height = recyclerViewHeight
    }

    private fun setObservers() {

        homeViewModel.apply {
            upcomingMoviesLiveData.observe(viewLifecycleOwner) {
                binding.swipeRefreshView.isRefreshing = false
                setDataToRecyclerView(it, binding.upcomingMoviesRecyclerView)
            }

            topRatedMoviesLiveData.observe(viewLifecycleOwner) {
                binding.swipeRefreshView.isRefreshing = false
                setDataToRecyclerView(it, binding.topRatedMoviesRecyclerView)
                setDataToRecyclerView(it, binding.recommendedMoviesRecyclerView)

                initFilterInfo(getString(R.string.language_default_selection), getString(R.string.release_year_default_selection))
                // Call to get last user preferences data
                getUserPreferences()
            }

            userPreferencesLiveData.observe(viewLifecycleOwner) {
                val filteredList = homeViewModel.getFilteredRecommendedList(it)

                setDataToRecyclerView(filteredList, binding.recommendedMoviesRecyclerView)
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

        binding.apply {
            swipeRefreshView.setOnRefreshListener {
                homeViewModel.getUpcomingMovies()
                homeViewModel.getTopRatedMovies()
            }

            filterLanguageButton.setOnClickListener {
                openListSelection(
                    homeViewModel.getRecommendedMoviesLanguages().toTypedArray()
                ) {
                    // If it's the default then pass the empty so we update correctly the filter
                    val languageFilter = if(it == getString(R.string.language_default_selection)) "" else it
                    homeViewModel.filterRecommendedMoviesByLanguage(languageFilter)
                }
            }

            filterYearButton.setOnClickListener {
                openListSelection(
                    homeViewModel.getRecommendedMoviesYears().toTypedArray()
                ) {
                    // If it's the default then pass the empty so we update correctly the filter
                    val yearFilter = if(it == getString(R.string.release_year_default_selection)) "" else it
                    homeViewModel.filterRecommendedMoviesByYear(yearFilter)
                }
            }
        }
    }

    private fun openListSelection(stringArray: Array<String>, selectionAction: (String) -> Unit) {
        val direction = HomeFragmentDirections
            .goToBottomSheetDialogListFromHome(
                stringArray,
                object : BottomSheetDialogListListener {
                    override fun onStringSelected(string: String) {
                        selectionAction.invoke(string)
                    }
                }
            )
        navigate(direction)
    }

    private fun setTopRatedRecyclerView(data : List<UIMovieItem>?) {
        setMoviesRecyclerView(binding.topRatedMoviesRecyclerView, data)
    }

    private fun setUpcomingRecyclerView(data : List<UIMovieItem>?) {
        setMoviesRecyclerView(binding.upcomingMoviesRecyclerView, data)
    }

    private fun setRecommendedRecyclerView(data : List<UIMovieItem>?) {
        val layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.home_recommended_grid_span))
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