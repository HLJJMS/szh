package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.NewFriendBean

class NewFriendAdapter :
    BaseQuickAdapter<NewFriendBean.ResultEntity, BaseViewHolder>(R.layout.item_new_friend) {
    override fun convert(holder: BaseViewHolder, item: NewFriendBean.ResultEntity) {
        holder.setText(R.id.tv_time,item.createdate)
        holder.setText(R.id.tv_name,item.name)
        if(item.state==0){
            holder.setVisible(R.id.no,true)
            holder.setVisible(R.id.ok,true)
            holder.setText(R.id.tv_detail,item.content)
        }else if (item.state==1){
            holder.setVisible(R.id.no,false)
            holder.setVisible(R.id.ok,false)
            holder.setText(R.id.tv_detail,"已拒绝")
        }else{
            holder.setVisible(R.id.no,false)
            holder.setVisible(R.id.ok,false)
            holder.setText(R.id.tv_detail,"已同意")
        }
    }
}