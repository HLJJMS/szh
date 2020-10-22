package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.BangdanBean

class CaiJingAdapter : BaseQuickAdapter<BangdanBean.ResultEntity.DirsListEntity, BaseViewHolder>(R.layout.item_bangdan) {
    override fun convert(holder: BaseViewHolder, item: BangdanBean.ResultEntity.DirsListEntity) {
        holder.setText(R.id.rb_name,item.title)
    }
}