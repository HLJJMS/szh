package com.example.szh.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

//class RecommendAdapter : BaseMultiItemQuickAdapter<QuickMultipleEntity, BaseViewHolder> {
//
//    fun MultipleItemQuickAdapter(data: List<QuickMultipleEntity?>?) {
//        super(data)
//        addItemType(QuickMultipleEntity.TEXT, R.layout.item_text_view)
//        addItemType(QuickMultipleEntity.IMG, R.layout.item_image_view)
//        addItemType(QuickMultipleEntity.IMG_TEXT, R.layout.item_img_text_view)
//    }
//
//    fun convert(helper: BaseViewHolder, item: QuickMultipleEntity) {
//        when (helper.itemViewType) {
//            QuickMultipleEntity.TEXT -> helper.setText(R.id.tv, item.getContent())
//            QuickMultipleEntity.IMG_TEXT -> when (helper.layoutPosition % 2) {
//                0 -> helper.setImageResource(R.id.iv, R.mipmap.animation_img1)
//                1 -> helper.setImageResource(R.id.iv, R.mipmap.animation_img2)
//                else -> {
//                }
//            }
//            else -> {
//            }
//        }
//    }
//}