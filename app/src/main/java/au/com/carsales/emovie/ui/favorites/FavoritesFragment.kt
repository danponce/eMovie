package au.com.carsales.emovie.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import au.com.carsales.emovie.R
import au.com.carsales.emovie.databinding.FragmentFavoritesBinding
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseDataBindingFragment
import au.com.carsales.emovie.utils.base.BaseNavFragment
import au.com.carsales.emovie.utils.base.databinding.SingleLayoutBindRecyclerAdapter
import au.com.carsales.emovie.utils.base.setBackButton
import au.com.carsales.emovie.utils.base.state.observeStateLiveData
import au.com.carsales.emovie.utils.compose.BaseToolbar
import au.com.carsales.emovie.utils.compose.ComposableAsyncImage
import au.com.carsales.emovie.utils.compose.composeContentView
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@AndroidEntryPoint
class FavoritesFragment : BaseNavFragment() {

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initObservers()

        return composeContentView(
            compositionStrategy = ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner),
            content = { ComposeView() } )
    }
    
    private fun initObservers() {
        favoritesViewModel.favoritesLiveData.observe(viewLifecycleOwner) {

        }
    }

    @Composable
    private fun ComposeView() {
        MaterialTheme {
            BaseToolbar(
                toolbarTitle = stringResource(
                    id = R.string.favorites_toolbar_title),
                onBackAction = { navigateBack()},
                paddingAction = {
                    GeneralView(paddingValues = it)
                },
            )
        }
    }

    @Composable
    private fun GeneralView(paddingValues: PaddingValues) {
        val context = LocalContext.current

        val backgroundColor =
            Color(ContextCompat.getColor(context, R.color.primaryDarkColor))

        val favoriteMovies by favoritesViewModel.favoritesLiveData.observeAsState()

        Column(
            Modifier
                .padding(paddingValues)
                .background(backgroundColor)
        ) {
            MoviesGrid(movies = favoriteMovies.orEmpty())
        }

    }

    @Composable
    fun MoviesGrid(movies: List<UIMovieItem>) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie)
            }
        }
    }

    @Composable
    fun MovieItem(movie : UIMovieItem) {
        ComposableAsyncImage(
            movie.getFormattedPosterPath(),
            R.drawable.ic_default_image_placeholder_48,
            30.dp
        ) { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = stringResource(id = R.string.favorite_movie_content_description),
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
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
    }
}