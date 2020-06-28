package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.BangdanBean

class BangdanAdapter : BaseQuickAdapter<BangdanBean.ResultBean.DirsListBean, BaseViewHolder>(R.layout.item_bangdan) {
    override fun convert(holder: BaseViewHolder, item: BangdanBean.ResultBean.DirsListBean) {
        holder.setText(R.id.rb_name,item.title)
    }

}
