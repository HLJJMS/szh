package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MyArticleBean
import com.diwaves.news.tools.MyGlide

class MyArticleAdapter :
    BaseQuickAdapter<MyArticleBean.ResultBean.ArticlesListBean, BaseViewHolder>(R.layout.item_my_artcle) {
    override fun convert(holder: BaseViewHolder, item: MyArticleBean.ResultBean.ArticlesListBean) {
//        holder.setText(R.id.tv_name, item.name.toString())
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_time, item.createdate)
        holder.setText(R.id.tv_detail, item.content)
        holder.setText(R.id.tv_read, item.view.toString() + "阅读")
        holder.setText(R.id.tv_comment, item.view.toString() + "评论")
        if (null != item.avatarUrl) {
            MyGlide.loadImage(context, item.avatarUrl.toString(), holder.getView(R.id.iv_head))
        }

        if (null != item.pic) {
            MyGlide.loadImage(context, item.pic.toString(), holder.getView(R.id.iv_detail))
        }
    }
}