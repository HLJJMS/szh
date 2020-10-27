package com.diwaves.news.mvp.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.diwaves.news.R
import com.diwaves.news.di.component.DaggerBuyVipComponent
import com.diwaves.news.di.module.BuyVipModule
import com.diwaves.news.mvp.contract.BuyVipContract
import com.diwaves.news.mvp.presenter.BuyVipPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_buy_vip.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 10/26/2020 16:01
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
class BuyVipActivity : BaseActivity<BuyVipPresenter>(), BuyVipContract.View {
    var m = "1"
    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerBuyVipComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .buyVipModule(BuyVipModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_buy_vip //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        iv_back.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            finish()
        }
        tv_1.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setRmbState(1)
        }
        tv_6.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setRmbState(2)
        }
        tv_12.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            setRmbState(3)
        }
        setRmbState(1)
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {

            showDialog()
        }
        tv_buy.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {
            startActivity(Intent(this, AddRmbActivity::class.java))
        }
    }

    override fun buySuccess() {
        finish()
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

    fun setRmbState(position: Int) {
        if (position == 1) {
            tv_1.setBackgroundResource(R.drawable.conner5_solid_yellow_stoken_white)
            tv_6.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_12.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_1.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            tv_6.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_12.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_1_time.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            tv_6_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_12_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            m = "1"
        } else if (position == 2) {
            tv_1.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_6.setBackgroundResource(R.drawable.conner5_solid_yellow_stoken_white)
            tv_12.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_1.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_6.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            tv_12.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_1_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_6_time.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            tv_12_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            m = "2"
        } else {
            tv_1.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_6.setBackgroundResource(R.drawable.bg_conner3_white)
            tv_12.setBackgroundResource(R.drawable.conner5_solid_yellow_stoken_white)
            tv_1.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_6.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_12.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            tv_1_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_6_time.setTextColor(ContextCompat.getColor(this, R.color.color_444444))
            tv_12_time.setTextColor(ContextCompat.getColor(this, R.color.color_F4AB2B))
            m = "3"
        }
    }


    fun showDialog() {
        val normalDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        normalDialog.setTitle("成为会员")
        normalDialog.setMessage("确定购买" + m + "月会员？");
        normalDialog.setPositiveButton(
            "确定"
        ) { dialog, which ->
            mPresenter?.buy(m)
        }
        normalDialog.setNegativeButton(
            "取消"
        ) { dialog, which ->

        }
        // 显示
        normalDialog.show()
    }
}
