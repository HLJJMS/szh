package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.BangdanBean

class BangdanAdapter : BaseQuickAdapter<BangdanBean.ResultEntity, BaseViewHolder>(R.layout.item_bangdan) {
   var  position=0
    override fun convert(holder: BaseViewHolder, item: BangdanBean.ResultEntity) {
        holder.setText(R.id.rb_name,item.title)
        if(holder.adapterPosition==position){
            holder.setBackgroundResource(R.id.rb_name, R.drawable.bg_conner3_solid_blue)
            holder.setTextColor(R.id.rb_name,R.color.color_white)
        }else{
            holder.setBackgroundResource(R.id.rb_name, R.color.qmui_config_color_white)
            holder.setTextColor(R.id.rb_name,R.color.color_020202)
        }
    }
    fun setClickPosition(position:Int){
        this.position = position
        notifyDataSetChanged()
    }

}
