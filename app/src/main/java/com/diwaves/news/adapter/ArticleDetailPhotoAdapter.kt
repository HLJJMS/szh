package com.diwaves.news.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.tools.MyGlide

class ArticleDetailPhotoAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_image) {
    override fun convert(holder: BaseViewHolder, item: String) {
        MyGlide.loadImage(context, item, holder.getView(R.id.iv_img) as ImageView)
    }
}