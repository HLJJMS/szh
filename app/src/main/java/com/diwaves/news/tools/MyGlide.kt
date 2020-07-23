package com.diwaves.news.tools

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import java.io.File

class MyGlide {
    companion object {
        fun loadImage(context: Context, url: String, iv: ImageView) {
            if(null!=url){
                Glide.with(context).load(url).apply(
                    RequestOptions().skipMemoryCache(true).diskCacheStrategy(
                        DiskCacheStrategy.NONE
                    )
                ).into(iv)
            }

        }

        fun loadImage(context: Context, file: File, iv: ImageView) {
            Glide.with(context).load(file).apply(
                RequestOptions().skipMemoryCache(true).diskCacheStrategy(
                    DiskCacheStrategy.NONE
                )
            ).into(iv)
        }

        fun loadImageCircle(context: Context, url: String, iv: ImageView) {
            if(null!=url){
                Glide.with(context).load(url).apply(
                    RequestOptions.bitmapTransform(CircleCrop())
                ).into(iv)
            }
        }

        fun loadImageCircle(context: Context, file: File, iv: ImageView) {
            Glide.with(context).load(file).apply(
                RequestOptions.bitmapTransform(CircleCrop())
            ).into(iv)
        }


    }
}