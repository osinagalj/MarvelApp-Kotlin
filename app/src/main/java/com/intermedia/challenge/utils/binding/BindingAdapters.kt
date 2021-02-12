package com.intermedia.challenge.utils.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.intermedia.challenge.data.models.Thumbnail

@BindingAdapter("imageThumbnail")
fun setImage(view: ImageView, thumbnail: Thumbnail) {
    val url = "${thumbnail.path}.${thumbnail.extension}".replace("http", "https")
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("android:isVisible")
fun View.setIsVisible(visible: Boolean?) {
    visibility = if (visible != null && visible) View.VISIBLE else View.GONE
}