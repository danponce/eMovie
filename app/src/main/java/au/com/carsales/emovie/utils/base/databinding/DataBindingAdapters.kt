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

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@BindingAdapter("loadImageUrl")
fun loadImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.ic_default_image_placeholder_48)
        .error(R.drawable.ic_baseline_broken_image_48)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}

@BindingAdapter("loadImageUrlInmediate")
fun loadImageUrlInmediate(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .asBitmap()
        .thumbnail(Glide.with(imageView.context).asBitmap().load(url).priority(Priority.IMMEDIATE).override(imageView.width, imageView.height))
        .load(url)
        .placeholder(R.drawable.ic_default_image_placeholder_48)
        .into(imageView)
}

@BindingAdapter("loadImageUrlWithRoundedCorners")
fun loadImageUrlWithRoundedCorners(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_default_image_placeholder_48)
        .error(R.drawable.ic_baseline_broken_image_48)
        .transform(CenterInside(), RoundedCorners(36))
        .into(imageView)
}

@BindingAdapter("loadImageUrlWithTopRoundedCorners")
fun loadImageUrlWithTopRoundedCorners(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_default_image_placeholder_48)
        .error(R.drawable.ic_baseline_broken_image_48)
        .transform(CenterInside(), GranularRoundedCorners(36f, 36f, 0f, 0f))
        .into(imageView)
}

@BindingAdapter("setHTMLText")
fun setHTMLText(textView: TextView, text: String?) {
    if(text == null) {
        return
    }

    textView.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
}