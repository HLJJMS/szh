package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.CommentBean
import com.diwaves.news.tools.MyGlide

class CommentAdapter : BaseQuickAdapter<CommentBean.ResultBean.RecordsBean, BaseViewHolder>(R.layout.item_comment) ,
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: CommentBean.ResultBean.RecordsBean) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_title,item.content)
        holder.setText(R.id.tv_time,item.createdatetime)
        holder.setText(R.id.tv_good,item.up.toString())

        if(null!=item.pic){
            MyGlide.loadImage(context,item.pic,holder.getView(R.id.iv_detail))
        }
//        if(null!=item.avatarUrl){
//            MyGlide.loadImage(context,item.avatarUrl,holder.getView(R.id.iv_head))
//        }else{
//
//        }
        MyGlide.loadImage(context,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3976806040,3211395236&fm=11&gp=0.jpg",holder.getView(R.id.iv_head))

    }
}