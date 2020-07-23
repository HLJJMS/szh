package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.BangdanBean

class CaiJingAdapter : BaseQuickAdapter<BangdanBean.ResultBean.DirsListBean, BaseViewHolder>(R.layout.item_bangdan_caijing) {
    override fun convert(holder: BaseViewHolder, item: BangdanBean.ResultBean.DirsListBean) {
        holder.setText(R.id.tv_name,item.title)
    }
}