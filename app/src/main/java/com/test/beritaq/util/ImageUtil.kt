package com.test.beritaq.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.test.beritaq.R

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String) {
    url.let {
        Glide.with(imageView)
            .load(url)
            .placeholder(R.drawable.empty_state)
            .error(R.drawable.empty_state)
            .into(imageView)
    }
}