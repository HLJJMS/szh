package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.RecommendBean
import com.diwaves.news.tools.MyGlide

class RecommendAdapter : BaseMultiItemQuickAdapter<RecommendBean.ResultEntity, BaseViewHolder>() {
   init {
        addItemType(0, R.layout.item_recommend_text)
        addItemType(1, R.layout.item_recommend_video)
    }
    override fun convert(helper: BaseViewHolder, item: RecommendBean.ResultEntity) {
        if (helper.itemViewType == 0) {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
            helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")
        } else {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
//            helper.setText(R.id.tv_pinglun, item.comment.toString() + "评论")

            if(null!=item.pic){
                MyGlide.loadImage(context,item.pic,helper.getView(R.id.iv_detail))
            }
            helper.setText(R.id.tv_article,item.contenttext)
            helper.setText(R.id.tv_name,item.author)
        }
    }


}