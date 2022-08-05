package au.com.carsales.emovie.utils.compose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import au.com.carsales.emovie.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

fun Fragment.composeContentView(
    compositionStrategy: ViewCompositionStrategy = ViewCompositionStrategy.DisposeOnDetachedFromWindow,
    context: Context? = requireContext(),
    content: @Composable () -> Unit
): ComposeView? {
    context ?: return null
    val view = ComposeView(context)
    view.setViewCompositionStrategy(compositionStrategy)
    view.setContent(content)
    return view
}

@Composable
fun BaseToolbar(
    toolbarTitle : String,
    onBackEnabled : Boolean = true,
    onBackAction : () -> Unit,
    backgroundColor: Color? = null,
    backButtonColor: Color = Color.White,
    textColor: Color = Color.White,
    paddingAction : @Composable (PaddingValues) -> Unit,
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {

    val context = LocalContext.current

    val primaryColor  = Color(ContextCompat.getColor(context, R.color.primaryColor))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    toolbarTitle,
                    fontSize = 18.sp,
                    color = textColor)
                },
                backgroundColor = backgroundColor ?: primaryColor,
                elevation = elevation,
                navigationIcon = {
                    if(onBackEnabled) {
                        IconButton(onClick = { onBackAction.invoke() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = backButtonColor
                            )
                        }
                    }
                }
            )
        },
    ) {
        paddingAction.invoke(it)
    }

}

@Composable
fun ComposableText(
    text: String,
    color: Color? = null,
    size: TextUnit? = null,
    topPadding: Dp = 0.dp,
    startPadding: Dp = 0.dp,
    bottomPadding: Dp = 0.dp,
    endPadding: Dp = 0.dp,
    fontFamily: FontFamily = FontFamily(Font(R.font.open_sans_regular))
) {

    Text(
        text = text,
        style = MaterialTheme.typography.body1.merge(),
        modifier = Modifier.padding(start = startPadding, top = topPadding, bottom = bottomPadding, end = endPadding ),
        fontSize = size ?: 17.sp,
        color = color ?: Color.DarkGray,
        fontFamily = fontFamily
    )
}

fun loadImageUrlWithCallback(context: Context, url: String, bitmapReadyListener : (Bitmap) -> Unit) {
    Glide.with(context).asBitmap()
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapReadyListener.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}

@Composable
fun ComposableImageWithBitmap(
    bitmap: Bitmap?,
    drawableResource: Int,
    placeHolderSize : Dp,
    content : @Composable (Bitmap) -> Unit,
    modifier: Modifier
) {
    Column(modifier) {
        when (bitmap == null) {
            true -> {
                Image(
                    painter = painterResource(drawableResource),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(placeHolderSize)
                )
            }

            false -> {
                content.invoke(bitmap)
            }
        }
    }
}

@Composable
fun ComposableAsyncImage(
    url: String,
    drawableResource: Int,
    placeHolderSize : Dp,
    modifier: Modifier,
    content : @Composable (Bitmap) -> Unit
) {
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    loadImageUrlWithCallback(LocalContext.current, url) {
        bitmap = it
    }

    ComposableImageWithBitmap(
        bitmap,
        drawableResource,
        placeHolderSize,
        content,
        modifier
    )
}
