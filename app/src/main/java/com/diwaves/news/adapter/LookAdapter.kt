package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.FocusListBean
import com.diwaves.news.tools.MyGlide
import kotlinx.android.synthetic.main.fragment_my.*

class LookAdapter : BaseQuickAdapter<FocusListBean.ResultDTO.RecordsDTO, BaseViewHolder>(R.layout.item_look) {
    override fun convert(holder: BaseViewHolder, item: FocusListBean.ResultDTO.RecordsDTO) {
        holder.setText(R.id.tv_name,item.user.name)
        holder.setText(R.id.tv_time,item.createdate)
        holder.setText(R.id.tv_read,item.view.toString() + "阅读")
        holder.setText(R.id.tv_comment,item.view.toString() + "评论")
        MyGlide.loadImage(context,item.user.avatarUrl,holder.getView(R.id.iv_head))
        if(null!=item?.audiopath?.toString()){
            MyGlide.loadImage(context,item.audiopath.toString(),holder.getView(R.id.iv_detail))
        }else{
            holder.setText(R.id.tv_title,item.title)
        }
        holder.setText(R.id.rb_detail,item.dirname)
    }
}