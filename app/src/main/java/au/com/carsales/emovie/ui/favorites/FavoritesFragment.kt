package au.com.carsales.emovie.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentFavoritesBinding
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.emovie.utils.base.setBackButton
import au.com.carsales.emovie.utils.base.state.observeStateLiveData
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@AndroidEntryPoint
class FavoritesFragment : BaseDataBindingFragment<FragmentFavoritesBinding>() {

    override fun layoutId(): Int = R.layout.fragment_favorites

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        initObservers()

        binding.newToolbar.setBackButton(requireActivity()) { navigateBack() }

        return binding.root
    }
    
    private fun initObservers() {
        favoritesViewModel.favoritesLiveData.observeStateLiveData(
            viewLifecycleOwner,
            onSuccess = { setFavoritesRecyclerView(it.data) }
        )
    }
    
    private fun setFavoritesRecyclerView(favoritesList : List<UIMovieItem>?) {
        binding.favoritesRecyclerView.apply { 
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = SingleLayoutBindRecyclerAdapter(
                R.layout.view_cell_favorite,
                favoritesList,
                clickHandler = { view, item ->

                    when(view.id) {
                        R.id.favoriteContainer -> goToDetailScreen(item)

                        R.id.favoriteImageView -> {
                            favoritesViewModel.deleteFromFavorites(item)
                        }
                    }
                    
                }
            )
        }
    }

    private fun goToDetailScreen(item : UIMovieItem) {
        val direction = FavoritesFragmentDirections.goToEpisodeDetailAction(item)

        navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        favoritesViewModel.getFavoritesTVShows()

        binding.viewModel = favoritesViewModel
    }
}