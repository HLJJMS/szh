package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MessageBean

class MessageAdapter :
    BaseQuickAdapter<MessageBean.ResultEntity, BaseViewHolder>(R.layout.item_message) {
    override fun convert(holder: BaseViewHolder, item: MessageBean.ResultEntity) {
        holder.setText(R.id.tv_name, item.name)
        holder.setText(R.id.tv_detail, item.content)
    }

}