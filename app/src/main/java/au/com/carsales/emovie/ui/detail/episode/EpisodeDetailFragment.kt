package au.com.carsales.emovie.ui.detail.episode

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import au.com.carsales.emovie.R
import au.com.carsales.emovie.utils.base.BaseNavFragment
import au.com.carsales.emovie.utils.compose.BaseToolbar
import au.com.carsales.emovie.utils.compose.ComposableText
import au.com.carsales.emovie.utils.compose.composeContentView
import au.com.carsales.emovie.utils.compose.loadImageUrlWithCallback

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class EpisodeDetailFragment : BaseNavFragment() {

    private val episodeDetailViewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

//        val args : EpisodeDetailFragmentArgs by navArgs()
//
//        episodeDetailViewModel.episode =  args.episode

        return composeContentView(
            compositionStrategy = ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner),
            content = { ComposeView() } )
    }

    @Composable
    private fun ComposeView() {

//        val episode = episodeDetailViewModel.episode

//        MaterialTheme {
//            BaseToolbar(
//                toolbarTitle = stringResource(
//                    id = R.string.episode_detail_season_and_name_title,
//                    episode?.season?: 0,
//                    episode?.number ?: 0,
//                    episode?.name.orEmpty()),
//                onBackAction = { navigateBack()},
//                paddingAction = {
////                                GeneralView(paddingValues = it, episode)
//
//                },
//            )
//        }
        
    }

//    @Composable
//    private fun GeneralView(paddingValues: PaddingValues, episode : EpisodeViewData?) {
//        val context = LocalContext.current
//
//        val backgroundColor =
//            Color(ContextCompat.getColor(context, R.color.primaryDarkColor))
//
//        Column(
//            Modifier
//                .padding(paddingValues)
//                .background(backgroundColor)
//                .fillMaxHeight()
//                .fillMaxWidth()
//        ) {
//            if(episode?.image != null) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 6.dp, bottom = 6.dp, end = 6.dp), horizontalArrangement = Arrangement.Center
//                ) {
//                    EpisodeImage(episode)
//                }
//            }
//
//            ComposableText(
//                text = if(episode?.summary.isNullOrEmpty()) {
//                    stringResource(id = R.string.episode_detail_no_summary_message)
//                } else {
//                    HtmlCompat.fromHtml(episode?.summary.orEmpty(), HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
//                       },
//                color = Color(ContextCompat.getColor(context, R.color.white)),
//                size = 16.sp,
//                startPadding = 16.dp,
//                endPadding = 16.dp,
//                topPadding = 12.dp
//            )
//        }
//    }
//
//    @Composable
//    private fun EpisodeImage(episode : EpisodeViewData?) {
//
//        var bitmap by remember { mutableStateOf<Bitmap?>(null) }
//
//        loadImageUrlWithCallback(episode?.image?.original.orEmpty(), LocalContext.current) {
//            bitmap = it
//        }
//
//        when(bitmap == null) {
//            true -> {
//                Image(
//                    painter = painterResource(R.drawable.ic_default_image_placeholder_48),
//                    contentDescription = "EpisodeImage",
//                    modifier = Modifier
//                        .size(50.dp),
//                    contentScale = ContentScale.Inside
//                )
//            }
//
//            false -> {
//                bitmap?.let {
//                    Image(
//                        bitmap = it.asImageBitmap(),
//                        contentDescription = "EpisodeImage",
//                        contentScale = ContentScale.Fit                     // clip to the circle shape
//                    )
//                }
//
//            }
//        }
//
//    }
}