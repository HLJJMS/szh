package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.BangdanBean

class CaiJingAdapter : BaseQuickAdapter<BangdanBean.ResultBean.DirsListBean, BaseViewHolder>(R.layout.item_bangdan_caijing) {
    override fun convert(holder: BaseViewHolder, item: BangdanBean.ResultBean.DirsListBean) {
        holder.setText(R.id.tv_name,item.title)
    }
}