package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.FocusListBean
import com.diwaves.news.bean.PingBiListBean
import com.diwaves.news.tools.MyGlide

class PingBiAdapter :
    BaseQuickAdapter<PingBiListBean.ResultBean.ListBean.RecordsBean, BaseViewHolder>(R.layout.item_look) {
    override fun convert(
        holder: BaseViewHolder,
        item: PingBiListBean.ResultBean.ListBean.RecordsBean
    ) {
        holder.setVisible(R.id.tv_name,false)
        holder.setText(R.id.tv_title, item?.title)
        holder.setVisible(R.id.tv_time, false)
        holder.setText(R.id.tv_detail, item.articles.content)
        holder.setText(R.id.tv_read, item.articles.view.toString() + "阅读")
        holder.setText(R.id.tv_comment, item.articles.view.toString() + "评论")
        holder.setVisible(R.id.iv_head,false)
        if (null != item.articles.pic) {
            MyGlide.loadImage(context, item.articles.pic, holder.getView(R.id.iv_detail))
        }
    }
}