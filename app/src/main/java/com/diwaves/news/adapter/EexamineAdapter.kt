package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MessageAuditBean

class EexamineAdapter  : BaseQuickAdapter<MessageAuditBean.ResultEntity, BaseViewHolder>(R.layout.item_examine)  {
    override fun convert(
        holder: BaseViewHolder,
        item: MessageAuditBean.ResultEntity
    ) {
        holder.setText(R.id.tv_name,item.articles.name)
        holder.setText(R.id.tv_time,item.datetime)
        holder.setText(R.id.tv_detail,item.articles.title)
    }
}