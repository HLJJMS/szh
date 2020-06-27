package com.example.szh.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.RecommendBean
import com.example.szh.tools.MyGlide

class RecommendAdapter : BaseMultiItemQuickAdapter<RecommendBean.ResultBean, BaseViewHolder>() {
   init {
        addItemType(1, R.layout.item_recommend_text)
        addItemType(2, R.layout.item_recommend_video)
    }
    override fun convert(helper: BaseViewHolder, item: RecommendBean.ResultBean) {
        if (helper.itemViewType == 1) {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
            helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
        } else {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
            helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
            MyGlide.loadImage(context,item.pic,helper.getView(R.id.iv_detail))
            helper.setText(R.id.tv_article,item.contenttext)
            helper.setText(R.id.tv_name,item.author)
        }
    }


}