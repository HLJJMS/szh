package com.diwaves.news.adapter

import androidx.core.content.ContextCompat
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R

class AddRmbAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_rmb_button) {
    var position = 10
    override fun convert(holder: BaseViewHolder, item: String) {

        if (position == holder.adapterPosition) {
            holder.setBackgroundResource(R.id.cl_bg, R.drawable.bg_conner3_solid_blue)
            holder.setTextColor(
                R.id.tv_number,
                ContextCompat.getColor(context, R.color.qmui_config_color_white)
            )
            holder.setTextColor(
                R.id.tv_danwei,
                ContextCompat.getColor(context, R.color.qmui_config_color_white)
            )
        } else {
            holder.setBackgroundResource(R.id.cl_bg, R.drawable.conner5_solid_white_stoken_gray)
            holder.setTextColor(
                R.id.tv_number,
                ContextCompat.getColor(context, R.color.color_020202)
            )
            holder.setTextColor(
                R.id.tv_danwei,
                ContextCompat.getColor(context, R.color.color_020202)
            )
        }
        holder.setText(R.id.tv_number, item)
        holder.setText(R.id.tv_danwei, "元")
    }

    fun setClickPosition(i: Int) {
        position = i
        notifyDataSetChanged()
    }
}