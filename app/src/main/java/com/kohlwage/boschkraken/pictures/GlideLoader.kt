package com.kohlwage.boschkraken.pictures

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.kohlwage.boschkraken.R
import javax.inject.Inject

class GlideLoader @Inject constructor() : PictureLoader {

    private val baseUrl = "https://image.tmdb.org/t/p/"
    private val smallSize = "w500/"
    private val original = "w500/"

    override fun loadOriginalPictureFromToken(imageView: ImageView, token: String?) {
        loadPicture(imageView, buildUrl(token, original))
    }

    override fun loadSmallPictureFromToken(imageView: ImageView, token: String?) {
        loadPicture(imageView, buildUrl(token, smallSize))
    }

    private fun loadPicture(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_movie_clapper_soft_small)
            .into(imageView)
            .clearOnDetach()
    }

    private fun buildUrl(token: String?, size: String): String = token?.let {
        "$baseUrl$size$token"
    } ?: ""

}