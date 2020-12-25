package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.CollectBean
import com.diwaves.news.network.Api
import com.diwaves.news.tools.MyGlide

class CollectAdapter :
    BaseQuickAdapter<CollectBean.ResultBean.ListBean.RecordsBean, BaseViewHolder>(R.layout.item_recommend_video) {
    override fun convert(
        holder: BaseViewHolder,
        item: CollectBean.ResultBean.ListBean.RecordsBean
    ) {
//        holder.setText(R.id.tv_name,item.name)
//        holder.setText(R.id.tv_title,item.title)
//        holder.setText(R.id.tv_time,item.createdate)
//        holder.setText(R.id.tv_detail,item.content)
//        holder.setText(R.id.tv_read,item.view.toString() + "阅读")
//        holder.setText(R.id.tv_comment,item.view.toString() + "评论")
//        if(null!=item.avatarUrl){
//            MyGlide.loadImage(context,item.avatarUrl,holder.getView(R.id.iv_head))
//        }
//        if(null!=item.pic){
//            MyGlide.loadImage(context,item.pic,holder.getView(R.id.iv_detail))
//        }


        MyGlide.loadImage(context, Api.BASE_URL + item.pic, holder.getView(R.id.iv_head))
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.rb_detail, item.dirname)
        holder.setText(R.id.tv_time, item.createdate)
        holder.setText(R.id.tv_look, item.view.toString() + "阅读  " + item.comment + "次推荐")
//            MyGlide.loadImage(context, Api.BASE_URL+item.avatarUrl,helper.getView(R.id.iv_head))
        MyGlide.loadImage(context, Api.BASE_URL + item.pic, holder.getView(R.id.iv_photo))
        holder.setText(R.id.tv_name, item.website.toString())
    }
}