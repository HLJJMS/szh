package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.FriendListBean
import com.diwaves.news.tools.MyGlide

class FansAndLookAdapter(type: Int) :
    BaseQuickAdapter<FriendListBean.ResultBean, BaseViewHolder>(R.layout.item_fans_look) {
    //0粉丝1关注
    private var type = 0

    init {
        this.type = type
    }

    override fun convert(holder: BaseViewHolder, item: FriendListBean.ResultBean) {
        if (null != item.avatarUrl) {
            MyGlide.loadImage(context, item.avatarUrl, holder.getView(R.id.iv_head))
        }
        holder.setText(R.id.tv_name, item.name)
        if (type == 0) {
            holder.setVisible(R.id.rb_no, true)
        } else {
            holder.setVisible(R.id.rb_ok, true)
        }

    }
}