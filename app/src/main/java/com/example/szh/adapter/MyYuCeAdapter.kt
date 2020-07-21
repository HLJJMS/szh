package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.YuCeBean

class MyYuCeAdapter :
    BaseQuickAdapter<YuCeBean.ResultBean.ListBean.RecordsBean, BaseViewHolder>(R.layout.item_yuce) {
    override fun convert(holder: BaseViewHolder, item: YuCeBean.ResultBean.ListBean.RecordsBean) {
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_detail, item.comment)
        if (item.state == 1) {
            holder.setText(R.id.tv_result, "新预测")
        } else if (item.state == 2) {
            holder.setText(R.id.tv_result, "预测成功")
        } else {
            holder.setText(R.id.tv_result, "预测失败")
        }
    }
}