package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.FriendListBean
import com.example.szh.tools.MyGlide

class FansAndLookAdapter : BaseQuickAdapter<FriendListBean.ResultBean,BaseViewHolder>{
    constructor(
        type:Int
    ) : super(R.layout.item_fans_look)
    private var type = 0
    init {
        this.type = type
    }
    override fun convert(holder: BaseViewHolder, item: FriendListBean.ResultBean) {
       if(null!=item.avatarUrl){
           MyGlide.loadImage(context,item.avatarUrl,holder.getView(R.id.iv_head))
       }
        holder.setText(R.id.tv_name,item.name)

    }
}