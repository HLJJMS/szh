package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.MyCommentBean
import com.diwaves.news.tools.MyGlide

class MyCommentAdapter :
    BaseQuickAdapter<MyCommentBean.ResultBean.ListBean.RecordsBean, BaseViewHolder>(R.layout.item_recommend_video) {
    override fun convert(
        helper: BaseViewHolder,
        item: MyCommentBean.ResultBean.ListBean.RecordsBean
    ) {
        helper.setText(R.id.tv_title, item.content)
        helper.setText(R.id.rb_detail, item.articles.dirname)
        helper.setText(R.id.tv_time, item.articles.createdate)
        if (null != item.articles.avatarUrl) {
            MyGlide.loadImage(context, item.articles.avatarUrl, helper.getView(R.id.iv_detail))
        }

//        helper.setText(R.id.tv_article, item.articles.contenttext)
        helper.setText(R.id.tv_name, item.articles.author)
    }
}