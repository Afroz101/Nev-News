package com.utlis

import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.R

@BindingAdapter("imageUrl")
fun ImageView.setImageFromUrl(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.abc_ic_clear_material)
        .into(this);

}

@BindingAdapter("customDate")
fun TextView.setNewsDate(date: String) {
   this.text = DataFormat.formatDate(date)

}