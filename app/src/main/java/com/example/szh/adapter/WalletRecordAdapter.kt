package com.example.szh.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.szh.R
import com.example.szh.bean.WelltaRecordBean

class WalletRecordAdapter() :
    BaseQuickAdapter<WelltaRecordBean.ResultBean, BaseViewHolder>(R.layout.item_wallet_record) {

    override fun convert(holder: BaseViewHolder, item: WelltaRecordBean.ResultBean) {
        holder.setText(R.id.tv_title, item.content)
//        增加或减少币1 增加 2减少
        if (item.up.equals("1")) {
            holder.setText(R.id.tv_number, "+" + item.coincount)
        } else {
            holder.setText(R.id.tv_number, "-" + item.coincount)
        }
        holder.setText(R.id.tv_time, item.createdatetime)
    }
}