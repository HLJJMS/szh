package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.CommentBean
import com.diwaves.news.bean.YuCeCommentBean
import com.diwaves.news.tools.MyGlide

class YuCeCommentAdapter :
    BaseQuickAdapter<YuCeCommentBean, BaseViewHolder>(R.layout.item_yuce_comment),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: YuCeCommentBean) {
        holder.setText(R.id.tv_name, item.name)
        holder.setText(R.id.tv_title, item.comment)
        holder.setText(R.id.tv_time, item.createdatetime)
        holder.setText(R.id.tv_good, item.up.toString())
        MyGlide.loadImage(
            context,
            item.avatarUrl,
            holder.getView(R.id.iv_head)
        )
        if (item.option == 1) {
            holder.setText(R.id.tv_zhang, "大涨")
        } else if (item.option == 2) {
            holder.setText(R.id.tv_zhang, "涨")
        } else if (item.option == 3) {
            holder.setText(R.id.tv_zhang, "平")
        } else if (item.option == 4) {
            holder.setText(R.id.tv_zhang, "跌")
        } else if (item.option == 5) {
            holder.setText(R.id.tv_zhang, "大跌")
        }


    }
}