package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.CollectBean
import com.example.szh.bean.FocusListBean
import com.example.szh.tools.MyGlide

class CollectAdapter : BaseQuickAdapter<CollectBean.ResultBean.ListBean.RecordsBean, BaseViewHolder>(R.layout.item_look) {
    override fun convert(holder: BaseViewHolder, item: CollectBean.ResultBean.ListBean.RecordsBean) {
        holder.setText(R.id.tv_name,item.name)
        holder.setText(R.id.tv_title,item.title)
        holder.setText(R.id.tv_time,item.createdate)
        holder.setText(R.id.tv_detail,item.content)
        holder.setText(R.id.tv_read,item.view.toString() + "阅读")
        holder.setText(R.id.tv_comment,item.view.toString() + "评论")
        if(null!=item.avatarUrl){
            MyGlide.loadImage(context,item.avatarUrl,holder.getView(R.id.iv_head))
        }
        if(null!=item.pic){
            MyGlide.loadImage(context,item.pic,holder.getView(R.id.iv_detail))
        }
    }
}