package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.FriendListBean
import com.diwaves.news.tools.MyGlide

class FriendListAdapter : BaseQuickAdapter<FriendListBean.ResultBean, BaseViewHolder>(R.layout.item_friend) {
    override fun convert(holder: BaseViewHolder, item: FriendListBean.ResultBean) {
        holder.setText(R.id.tv_name,item.name)
        if (null!=item.avatarUrl){
            MyGlide.loadImage(context,item.avatarUrl,holder.getView(R.id.iv_head))
        }

    }
}