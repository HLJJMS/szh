package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.TypeListBean

class TypeListAdapter :BaseQuickAdapter<TypeListBean.ResultBean.RecordsBean,BaseViewHolder>(R.layout.item_recommend_text) ,
    LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: TypeListBean.ResultBean.RecordsBean) {
        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.rb_detail, item.dirname)
        helper.setText(R.id.tv_time, item.createdate)
        helper.setText(R.id.tv_name, item.website)
//        helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
    }
}