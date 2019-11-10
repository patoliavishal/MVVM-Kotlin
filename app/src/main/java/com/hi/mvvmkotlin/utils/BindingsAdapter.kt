package com.hi.mvvmkotlin.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hi.mvvmkotlin.R

/**
 * Created by Vishal Patel on 11/10/19.
 */
@SuppressLint("CheckResult")
@BindingAdapter("backgroundUrl")
fun setBackgroundImage(imageView: ImageView, id: Int?) {
    val requestOptions = RequestOptions()
    requestOptions.centerCrop()

    Glide.with(imageView.context)
        .load(id)
        .apply(requestOptions)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)
}

@SuppressLint("CheckResult")
@BindingAdapter("circularImageUrl")
fun setCircularImageUrl(imageView: ImageView, url: String) {

    if (url.isNotEmpty()) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
    }
}