package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.tools.MyGlide

class AddPhotoAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_select_photo) {
    override fun convert(holder: BaseViewHolder, item: String) {
        if (!item.equals("")) {
            MyGlide.loadImage(context, item, holder.getView(R.id.iv_img))
        }
    }
}