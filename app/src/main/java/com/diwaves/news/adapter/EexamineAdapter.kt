package com.diwaves.news.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MessageAuditBean
import com.diwaves.news.tools.MyGlide

class EexamineAdapter  : BaseQuickAdapter<MessageAuditBean.ResultEntity, BaseViewHolder>(R.layout.item_examine)  {
    override fun convert(
        holder: BaseViewHolder,
        item: MessageAuditBean.ResultEntity
    ) {

        MyGlide.loadImageCircle(context,"https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2070453827,1163403148&fm=26&gp=0.jpg",holder.getView(R.id.iv_head))
        holder.setText(R.id.tv_name,item.articles.author)
        holder.setText(R.id.tv_time,item.datetime)
        holder.setText(R.id.tv_detail,item.articles.title)
    }
}