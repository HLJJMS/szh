package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.TypeListBean
import com.diwaves.news.network.Api
import com.diwaves.news.tools.MyGlide

class KListAdapter :BaseQuickAdapter<TypeListBean.ResultBean.RecordsBean,BaseViewHolder>(R.layout.item_caijing) ,
    LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: TypeListBean.ResultBean.RecordsBean) {

    }
}