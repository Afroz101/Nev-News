package com.utlis

import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R


@BindingAdapter("imageUrl")
fun ImageView.setImageFromUrl(imageUrl: String) {
    Glide.with(context)
        .load(imageUrl)
        .centerCrop()
        .placeholder(R.drawable.image_not_available)
        .into(this);

}

@BindingAdapter("customDate")
fun TextView.setNewsDate(date: String) {
    this.text = DataFormat.formatDate(date)

}


@BindingAdapter("newsUrl")
fun WebView.setNewsUrl(newsUrl: String) {
    this.loadUrl(newsUrl)
}