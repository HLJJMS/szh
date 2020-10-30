package com.diwaves.news.adapter

import android.widget.RadioButton
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.diwaves.news.R

class PopRadioAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_radio) {
    var position = -1
    override fun convert(holder: BaseViewHolder, item: String) {
        var view = holder.getView<RadioButton>(R.id.radio)
        view.setText(item)
        if (position == holder.adapterPosition) {
            view.isChecked = true
        } else {
            view.isChecked = false
        }
    }

    fun setPositionAdapter(position: Int) {
        this.position = position
        notifyDataSetChanged()
    }
}