package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MyItemBean

class MyFramgentAdapter(list: List<MyItemBean>?) : BaseQuickAdapter<MyItemBean, BaseViewHolder>(R.layout.item_my
) {
    override fun convert(holder: BaseViewHolder, item: MyItemBean) {
        holder.setText(R.id.tv_name,item.text)
        holder.setImageResource(R.id.image,item.img)
        holder.setText(R.id.tv_number,item.number)
    }
}