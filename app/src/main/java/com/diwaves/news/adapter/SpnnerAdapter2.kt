package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.BangdanBean

class SpnnerAdapter2 :
    BaseQuickAdapter<BangdanBean.ResultEntity.DirsListEntity, BaseViewHolder>(R.layout.item_text) {

    override fun convert(holder: BaseViewHolder, item:BangdanBean.ResultEntity.DirsListEntity) {
        holder.setBackgroundResource(R.id.tv_txt, R.color.qmui_config_color_white)
        holder.setTextColor(R.id.tv_txt, R.color.color_020202)
        holder.setText(R.id.tv_txt,item.title)
    }


}
