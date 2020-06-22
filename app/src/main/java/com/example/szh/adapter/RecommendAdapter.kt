package com.example.szh.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.bean.RecommendBean

class RecommendAdapter : BaseMultiItemQuickAdapter<RecommendBean, BaseViewHolder>() {

    fun RecommendAdapter(data: List<RecommendBean?>?) {
        super(data)
        addItemType(QuickMultipleEntity.TEXT, R.layout.item_text_view)
        addItemType(QuickMultipleEntity.IMG, R.layout.item_image_view)
        addItemType(QuickMultipleEntity.IMG_TEXT, R.layout.item_img_text_view)
    }

    override fun convert(helper: BaseViewHolder, item: RecommendBean) {
        if (helper.itemViewType == 0) {

        } else {

        }
    }
}