package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.TypeListBean

class TypeListAdapter :BaseQuickAdapter<TypeListBean.ResultBean.RecordsBean,BaseViewHolder>(R.layout.item_recommend_text) {
    override fun convert(helper: BaseViewHolder, item: TypeListBean.ResultBean.RecordsBean) {
        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.rb_detail, item.dirname)
        helper.setText(R.id.tv_time, item.createdate)
        helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
    }
}