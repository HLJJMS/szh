package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.TypeListBean
import com.diwaves.news.network.Api
import com.diwaves.news.tools.MyGlide

class TypeListAdapter :BaseQuickAdapter<TypeListBean.ResultBean.RecordsBean,BaseViewHolder>(R.layout.item_recommend_video) ,
    LoadMoreModule {
    override fun convert(helper: BaseViewHolder, item: TypeListBean.ResultBean.RecordsBean) {
        MyGlide.loadImage(context,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3976806040,3211395236&fm=11&gp=0.jpg",helper.getView(R.id.iv_head))

        helper.setText(R.id.tv_title, item.title)
        helper.setText(R.id.rb_detail, item.dirname)
        helper.setText(R.id.tv_time, item.createdate)
        helper.setText(R.id.tv_look,item.view.toString() + "阅读  " + item.comment + "次推荐")
//            MyGlide.loadImage(context, Api.BASE_URL+item.avatarUrl,helper.getView(R.id.iv_head))
        MyGlide.loadImage(context, Api.BASE_URL+item.pic,helper.getView(R.id.iv_photo))
        helper.setText(R.id.tv_name,item.website)
    }
}