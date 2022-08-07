package au.com.carsales.emovie.utils.base.databinding

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import au.com.carsales.emovie.R
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_baseline_movie_24)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_baseline_broken_image_48)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}

@BindingAdapter("loadImageUrlWithRoundedCorners")
fun loadImageUrlWithRoundedCorners(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_movie_24)
        .error(R.drawable.ic_baseline_broken_image_48)
        .transition(DrawableTransitionOptions.withCrossFade())
        .transform(CenterInside(), RoundedCorners(36))
        .into(imageView)
}

@BindingAdapter("loadImageUrlWithTopRoundedCorners")
fun loadImageUrlWithTopRoundedCorners(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_movie_24)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_baseline_broken_image_48)
        .transform(CenterInside(), GranularRoundedCorners(36f, 36f, 0f, 0f))
        .into(imageView)
}