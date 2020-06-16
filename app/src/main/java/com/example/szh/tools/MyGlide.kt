package com.example.szh.tools

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MyGlide {
    companion object {
        fun loadImageUrl(context: Context, url: String) {
            Glide.with(context).load(url).apply(
                RequestOptions().skipMemoryCache(true).diskCacheStrategy(
                    DiskCacheStrategy.NONE
                )
            )
        }


    }
}