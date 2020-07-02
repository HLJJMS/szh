package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.RecommendBean

class SearchAdapter : BaseQuickAdapter<RecommendBean.ResultBean, BaseViewHolder>(R.layout.item_recommend_text) {
    override fun convert(holder: BaseViewHolder, item: RecommendBean.ResultBean) {
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.rb_detail, item.dirname)
        holder.setText(R.id.tv_time, item.createdate)
        holder.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
    }
}