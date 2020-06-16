package com.example.szh.tools


import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.example.szh.R
import kotlinx.android.synthetic.main.loading.view.*


class LoaddingView() : PopupWindow() {
    lateinit var context: Context
    lateinit var animation: Animation
    lateinit var img: ImageView
    lateinit var view: View

    /*次构造方法*/
    constructor(context: Context) : this() {
        this.context = context
        setPopupWindow()
    }

    fun setPopupWindow() {
        this.width = LinearLayout.LayoutParams.MATCH_PARENT // 设置弹出窗口的宽
        this.height = LinearLayout.LayoutParams.MATCH_PARENT // 设置弹出窗口的高
        view = LayoutInflater.from(context).inflate(R.layout.loading, null)
        this.setContentView(view);
        //设置背景,这个没什么效果，不添加会报错
        setBackgroundDrawable(BitmapDrawable())
        // this.setBackgroundDrawable(new ColorDrawable(0x55000000));// 设置背景透明
        img = view.iv_loading
        animation = RotateAnimation(
            0f,
            359f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        animation.setDuration(500);
        animation.setRepeatCount(-1);//动画的重复次数
        animation.setFillAfter(true);//设置为true，动画转化结束后被应用
        img.startAnimation(animation);//开始动画

//        setOnDismissListener {
//            val lp: WindowManager.LayoutParams = context.getWindow().getAttributes()
//            lp.alpha = 1f
//            mActivity.getWindow().setAttributes(lp)
//        }

    }

    fun show() {
        this.showAtLocation(view, Gravity.CENTER, 0, 0)
    }

}