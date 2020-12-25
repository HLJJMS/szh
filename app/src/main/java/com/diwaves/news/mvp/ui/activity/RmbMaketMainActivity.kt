package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.diwaves.news.R
import com.diwaves.news.bean.KListBean
import com.diwaves.news.bean.YuCeCommentUpBean
import com.diwaves.news.bean.YuCeDetail
import com.diwaves.news.di.component.DaggerRmbMaketMainComponent
import com.diwaves.news.di.module.RmbMaketMainModule
import com.diwaves.news.mvp.contract.RmbMaketMainContract
import com.diwaves.news.mvp.presenter.RmbMaketMainPresenter
import com.diwaves.news.tools.MyToast
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_rmb_maket_main.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 07/09/2020 11:22
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
//预测界面
class RmbMaketMainActivity : BaseActivity<RmbMaketMainPresenter>(), RmbMaketMainContract.View {
    var type: String = "日线"
    var popupWindow: PopupWindow = PopupWindow()
    var view: View? = null
    var tv_20: TextView? = null
    var tv_50: TextView? = null
    var tv_100: TextView? = null
    var tv_200: TextView? = null
    var tv_ok: TextView? = null
    var tv_dazhang: TextView? = null
    var tv_zhang: TextView? = null
    var tv_ping: TextView? = null
    var tv_die: TextView? = null
    var tv_dadie: TextView? = null
    var iv_check: ImageView? = null
    var tv_title_type: TextView? = null
    var hide = 1 // 0 公开1 不公开
    var option = 1
    var ag = "20"
    var predictid = ""
    var floatState = ""
    var et_text: EditText? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRmbMaketMainComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .rmbMaketMainModule(RmbMaketMainModule(this))
            .build()
            .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_rmb_maket_main //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    fun setPopWindow() {
        view = LayoutInflater.from(this).inflate(R.layout.pop_forecate, null);
        popupWindow.contentView = view
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true)
        popupWindow.setFocusable(true) //点击返回键取消
        popupWindow.setBackgroundDrawable(BitmapDrawable())
        tv_20 = view?.findViewById(R.id.tv_20);
        tv_50 = view?.findViewById(R.id.tv_50);
        tv_100 = view?.findViewById(R.id.tv_100);
        tv_200 = view?.findViewById(R.id.tv_200);
        tv_ok = view?.findViewById(R.id.tv_ok);
        et_text = view?.findViewById(R.id.et_text);
        tv_dazhang = view?.findViewById(R.id.tv_dazhang);
        tv_zhang = view?.findViewById(R.id.tv_zhang);
        tv_ping = view?.findViewById(R.id.tv_ping);
        tv_die = view?.findViewById(R.id.tv_die);
        tv_dadie = view?.findViewById(R.id.tv_dadie);
        iv_check = view?.findViewById(R.id.iv_check);
        tv_title_type = view?.findViewById(R.id.tv_title_type);
        tv_20?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            tv_20?.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_50?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_100?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_200?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            ag = "20"
        }
        tv_50?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            tv_20?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_50?.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_100?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_200?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            ag = "50"
        }
        tv_100?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            tv_20?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_50?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_100?.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_200?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            ag = "100"
        }
        tv_200?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            tv_20?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_50?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_100?.setBackgroundResource(R.drawable.bg_conner3_solid_blue)
            tv_200?.setBackgroundResource(R.drawable.bg_conner3_white)
            ag = "200"
        }
        popupWindow.setOnDismissListener {
            val lp = window.attributes
            lp.alpha = 1f
            window.attributes = lp
        }
        tv_title_type?.text = floatState
        tv_dazhang?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            option = 1
            tv_title_type?.text = tv_dazhang?.text
        }
        tv_zhang?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            option = 2
            tv_title_type?.text = tv_zhang?.text
        }
        tv_ping?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            option = 3
            tv_title_type?.text = tv_ping?.text
        }
        tv_die?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            option = 4
            tv_title_type?.text = tv_die?.text
        }
        tv_dadie?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            option = 5
            tv_title_type?.text = tv_dadie?.text
        }
        iv_check?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            if (hide == 0) {
                hide = 1
                iv_check?.setImageResource(R.mipmap.ic_check_on)
            } else {
                hide = 0
                iv_check?.setImageResource(R.mipmap.ic_check_off)
            }
        }
        tv_ok?.clicks()?.throttleFirst(500, TimeUnit.MILLISECONDS)?.subscribe {
            mPresenter?.postData(ag, predictid, option.toString(), hide.toString())
        }
    }

    fun showPopWindow() {
        val lp = window.attributes
        lp.alpha = 0.5f
        window.attributes = lp
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0)
    }

    override fun initData(savedInstanceState: Bundle?) {
        tv_hot.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
        }
        tv_new.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            tv_hot.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
            tv_new.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
        }
//        tv_day.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            tv_week.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            tv_moon.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            tv_day.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
//            type = "日线"
////            mPresenter?.getData(intent.getStringExtra("id"), type)
//        }
//        tv_moon.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            tv_week.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            tv_moon.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
//            tv_day.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            type = "月线"
////            mPresenter?.getData(intent.getStringExtra("id"), type)
//        }
//        tv_week.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
//            tv_day.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            tv_moon.setTextColor(ContextCompat.getColor(this, R.color.color_020202))
//            tv_week.setTextColor(ContextCompat.getColor(this, R.color.color_137ED0))
//            type = "周线"
////            mPresenter?.getData(intent.getStringExtra("id"), type)
//        }
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            if (!floatState.equals("")) {
                showPopWindow()
            } else {
                MyToast().makeToast(this, "=请选择选项")
            }

        }

        mPresenter?.getData(intent.getStringExtra("type"))
        setPopWindow()
    }

    override fun success(bean: YuCeDetail) {
        tv_title.setText(bean.d.predict.title)
        tv_detail1.setText(bean.d.predict.option1)
        tv_detail2.setText(bean.d.predict.option2)
        tv_detail3.setText(bean.d.predict.option3)
        tv_detail4.setText(bean.d.predict.option4)
        tv_detail5.setText(bean.d.predict.option5)
        predictid = bean.d.predict.id.toString()
        iv_check1.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            iv_check1.setImageResource(R.mipmap.ic_check_on)
            iv_check2.setImageResource(R.mipmap.ic_check_off)
            iv_check3.setImageResource(R.mipmap.ic_check_off)
            iv_check4.setImageResource(R.mipmap.ic_check_off)
            iv_check5.setImageResource(R.mipmap.ic_check_off)
            tv_ag.setText(bean.d.predict.option1value.toString())
            floatState = "大涨"
        }
        iv_check2.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            iv_check1.setImageResource(R.mipmap.ic_check_off)
            iv_check2.setImageResource(R.mipmap.ic_check_on)
            iv_check3.setImageResource(R.mipmap.ic_check_off)
            iv_check4.setImageResource(R.mipmap.ic_check_off)
            iv_check5.setImageResource(R.mipmap.ic_check_off)
            tv_ag.setText(bean.d.predict.option2value.toString())
            floatState = "涨"
        }
        iv_check3.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            iv_check1.setImageResource(R.mipmap.ic_check_off)
            iv_check2.setImageResource(R.mipmap.ic_check_off)
            iv_check3.setImageResource(R.mipmap.ic_check_on)
            iv_check4.setImageResource(R.mipmap.ic_check_off)
            iv_check5.setImageResource(R.mipmap.ic_check_off)
            tv_ag.setText(bean.d.predict.option3value.toString())
            floatState = "平"
        }
        iv_check4.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            iv_check1.setImageResource(R.mipmap.ic_check_off)
            iv_check2.setImageResource(R.mipmap.ic_check_off)
            iv_check3.setImageResource(R.mipmap.ic_check_off)
            iv_check4.setImageResource(R.mipmap.ic_check_on)
            iv_check5.setImageResource(R.mipmap.ic_check_off)
            tv_ag.setText(bean.d.predict.option4value.toString())
            floatState = "跌"
        }
        iv_check5.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            iv_check1.setImageResource(R.mipmap.ic_check_off)
            iv_check2.setImageResource(R.mipmap.ic_check_off)
            iv_check3.setImageResource(R.mipmap.ic_check_off)
            iv_check4.setImageResource(R.mipmap.ic_check_off)
            iv_check5.setImageResource(R.mipmap.ic_check_on)
            tv_ag.setText(bean.d.predict.option5value.toString())
            floatState = "大跌"
        }

    }

    override fun addSuccess() {
        popupWindow.dismiss()
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

}
