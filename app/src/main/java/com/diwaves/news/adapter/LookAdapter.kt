package com.diwaves.news.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R
import com.diwaves.news.bean.FocusListBean
import com.diwaves.news.tools.MyGlide
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton
import kotlinx.android.synthetic.main.fragment_my.*

class LookAdapter :
    BaseQuickAdapter<FocusListBean.ResultDTO.RecordsDTO, BaseViewHolder>(R.layout.item_look) {
    override fun convert(holder: BaseViewHolder, item: FocusListBean.ResultDTO.RecordsDTO) {
        MyGlide.loadImage(
            context,
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3976806040,3211395236&fm=11&gp=0.jpg",
            holder.getView(R.id.iv_head)
        )

        holder.setText(R.id.tv_name, item.user.name)
        holder.setText(R.id.tv_time, item.createdate)
        holder.setText(R.id.tv_read, item.view.toString() + "阅读")
        holder.setText(R.id.tv_comment, item.push.toString() + "推荐")
        var img = holder.getView(R.id.iv_detail) as ImageView
        var txt = holder.getView<TextView>(R.id.tv_detail)
        var imtTxt = holder.getView<ImageView>(R.id.iv_web_photo)
        var comment = holder.getView<TextView>(R.id.tv_comment)

        //        MyGlide.loadImage(context,item.user.avatarUrl,holder.getView(R.id.iv_head))
        holder.setText(R.id.rb_detail, item.dirname)
        if (item.pushid.equals("")) {
            if (null != item?.audiopath?.toString()) {
                val parts: MutableList<String> = item.audiopath.toString().split(",").toMutableList()
                    MyGlide.loadImage(
                        context,
                        parts.get(0),
                        holder.getView(R.id.iv_detail)
                    )


                img.visibility = View.VISIBLE
                holder.setText(R.id.tv_title, item.content)
            } else {
                img.visibility = View.GONE
                holder.setText(R.id.tv_title, item.title)
            }
            txt.visibility = View.GONE
            imtTxt.visibility = View.GONE
            comment.visibility = View.VISIBLE
            holder.getView<ImageView>(R.id.iv_go).visibility = View.VISIBLE
            holder.getView<TextView>(R.id.tv_tui).visibility = View.VISIBLE
            holder.getView<QMUIRoundButton>(R.id.rb_detail).visibility = View.VISIBLE
        } else {
            img.visibility = View.GONE
            txt.visibility = View.VISIBLE
            imtTxt.visibility = View.VISIBLE
            txt.setText(item.content)
            holder.setText(R.id.tv_read, "推送" + item.view.toString() + "银币")
            holder.getView<ImageView>(R.id.iv_go).visibility = View.INVISIBLE
            holder.getView<TextView>(R.id.tv_tui).visibility = View.INVISIBLE
            holder.getView<QMUIRoundButton>(R.id.rb_detail).visibility = View.INVISIBLE
            holder.getView<TextView>(R.id.tv_comment).visibility = View.INVISIBLE
        }
    }
}