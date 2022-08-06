package au.com.carsales.emovie.ui.favorites

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import au.com.carsales.emovie.R
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.BaseNavFragment
import au.com.carsales.emovie.utils.base.views.EmptyAllPurposeView
import au.com.carsales.emovie.utils.compose.*
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
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

        return composeContentView(
            compositionStrategy = ViewCompositionStrategy.DisposeOnLifecycleDestroyed(
                viewLifecycleOwner
            ),
            content = { ComposeView() })
    }

    @Composable
    private fun ComposeView() {
        MaterialTheme {
            BaseToolbarWithImage(
                elevation = 0.dp,
                backgroundColor = colorResource(id = R.color.black),
                onBackAction = { navigateBack() },
                imageResource = R.drawable.ic_brand_logo,
                paddingAction = {
                    GeneralView(paddingValues = it)
                }
            )
        }
    }

    @Composable
    private fun GeneralView(paddingValues: PaddingValues) {
        val context = LocalContext.current

        val backgroundColor =
            Color(ContextCompat.getColor(context, R.color.black))

        val favoriteMovies by favoritesViewModel.favoritesLiveData.observeAsState()

        Column(
            Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(paddingValues)
                .background(backgroundColor)
        ) {

            ComposableText(
                text = stringResource(id = R.string.favorites_title),
                color = Color.White,
                size = 20.sp,
                fontFamily = FontFamily(Font(R.font.open_sans_semi_bold)),
                bottomPadding = 12.dp,
                topPadding = 12.dp,
                startPadding = 16.dp
            )

            when {
                favoriteMovies.isNullOrEmpty() -> {
                    EmptyMoviesScreen(message = stringResource(id = R.string.favorites_empty_message))
                }

                else -> MoviesGrid(movies = favoriteMovies.orEmpty())
            }

        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun MoviesGrid(movies: List<UIMovieItem>) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.padding(start = 10.dp)
        ) {
            items(movies) { movie ->
                Row(Modifier.animateItemPlacement(tween(durationMillis = 250))) {
                    MovieItem(movie)
                }
            }
        }
    }

    @Composable
    fun MovieItem(movie: UIMovieItem) {
        ConstraintLayout {

            val (image, button) = createRefs()

            ComposableAsyncImage(
                movie.getFormattedPosterPath(),
                R.drawable.ic_default_image_placeholder_48,
                30.dp,
                modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            ) { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = stringResource(id = R.string.favorite_movie_content_description),
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .clickable { goToDetailScreen(movie) }
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                contentDescription = stringResource(id = R.string.favorite_movie_favorite_btn_description),
                tint = colorResource(id = R.color.secondaryColor),
                modifier = Modifier
                    .constrainAs(button) {
                        top.linkTo(image.top)
                        end.linkTo(image.end)
                    }
                    .width(36.dp)
                    .height(36.dp)
                    .clickable { removeFavorite(movie) }
            )

        }
    }

    @Composable
    fun EmptyMoviesScreen(message: String) {
        ConstraintLayout(modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .background(colorResource(id = R.color.black))
        ) {
            val (circle, loader, text) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(circle) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.white))
                    .size(120.dp)
            )

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .constrainAs(loader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(start = 20.dp)
            ) {
                Loader()
            }

            Text(
                text = message,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .padding(top = 30.dp, start = 60.dp, end = 60.dp)
                    .constrainAs(text) {
                        top.linkTo(loader.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
        }
    }

    @Composable
    fun Loader() {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.movie))
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
    }

    private fun removeFavorite(movie: UIMovieItem) {
        favoritesViewModel.deleteFromFavorites(movie)
    }

    private fun goToDetailScreen(item: UIMovieItem) {
        val direction = FavoritesFragmentDirections.goToMovieDetailsFromFavoriteAction(item)

        navigate(direction)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesViewModel.getFavoritesTVShows()
    }
}