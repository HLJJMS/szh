package com.diwaves.news.adapter

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.RecommendBean
import com.diwaves.news.network.Api
import com.diwaves.news.tools.MyGlide
import kotlinx.android.synthetic.main.fragment_my.*

class RecommendAdapter : BaseMultiItemQuickAdapter<RecommendBean.ResultEntity, BaseViewHolder>() {
   init {
        addItemType(0, R.layout.item_recommend_video)
        addItemType(1, R.layout.item_recommend_video)
    }
    override fun convert(helper: BaseViewHolder, item: RecommendBean.ResultEntity) {
        MyGlide.loadImage(context,"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3976806040,3211395236&fm=11&gp=0.jpg",helper.getView(R.id.iv_head))

        if (helper.itemViewType == 0) {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
            helper.setText(R.id.tv_look,item.view.toString() + "阅读  " + item.comment + "推送")
//            MyGlide.loadImage(context, Api.BASE_URL+item.avatarUrl,helper.getView(R.id.iv_head))
            MyGlide.loadImage(context, Api.BASE_URL+item.pic,helper.getView(R.id.iv_photo))
            helper.setText(R.id.tv_name,item.website)
        } else {
            helper.setText(R.id.tv_title, item.title)
            helper.setText(R.id.rb_detail, item.dirname)
            helper.setText(R.id.tv_time, item.createdate)
            helper.setText(R.id.tv_look,item.view.toString() + "阅读  " + item.comment + "推送")
//            MyGlide.loadImage(context, Api.BASE_URL+item.avatarUrl,helper.getView(R.id.iv_head))
            helper.setText(R.id.tv_name,item.website)
            MyGlide.loadImage(context, Api.BASE_URL+item.pic,helper.getView(R.id.iv_photo))
        }
    }


}