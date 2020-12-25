package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerYuCeComponent
import com.diwaves.news.di.module.YuCeModule
import com.diwaves.news.mvp.contract.YuCeContract
import com.diwaves.news.mvp.presenter.YuCePresenter

import com.diwaves.news.R

import com.diwaves.news.bean.KListBean
import kotlinx.android.synthetic.main.activity_yu_ce.*
import kotlinx.android.synthetic.main.activity_yu_ce.swipeLayout
import kotlinx.android.synthetic.main.fragment_look.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 12/21/2020 20:21
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
class YuCeActivity : BaseActivity<YuCePresenter>(), YuCeContract.View {


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerYuCeComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .yuCeModule(YuCeModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_yu_ce //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.getData("1")
    }

    override fun success(bean: KListBean) {
        swipeLayout.isRefreshing = false
        tv_title1.setText(bean.chuang.name)
        tv_time1.setText(bean.chuang.date)
        tv_number1.setText(bean.chuang.current + " " + bean.chuang.changePct + " " + bean.chuang.percentage)
        tv_title2.setText(bean.shang.name)
        tv_time2.setText(bean.shang.date)
        tv_number2.setText(bean.shang.current + " " + bean.shang.changePct + " " + bean.shang.percentage)
        tv_title.setText(bean.sheng.name)
        tv_time.setText(bean.sheng.date)
        tv_number.setText(bean.sheng.current + " " + bean.sheng.changePct + " " + bean.sheng.percentage)
        titleBar.setBackClick {
            finish()
        }
        if (null != intent.getStringExtra("title")) {
            titleBar.setCenterText(intent.getStringExtra("title"))
        }
        swipeLayout.setOnRefreshListener {
            mPresenter?.getData("1")
        }
        tv_k1.setOnClickListener {
            startActivity(
                Intent(this, KLineActivity::class.java).putExtra("type", "3")
                    .putExtra("title", tv_title1.text.toString())
                    .putExtra("number", tv_number1.text.toString())
            )
        }
        tv_k2.setOnClickListener {
            startActivity(
                Intent(this, KLineActivity::class.java).putExtra("type", "1")
                    .putExtra("title", tv_title2.text.toString())
                    .putExtra("number", tv_number2.text.toString())
            )
        }
        tv_k.setOnClickListener {
            startActivity(
                Intent(this, KLineActivity::class.java).putExtra("type", "2")
                    .putExtra("title", tv_title.text.toString())
                    .putExtra("number", tv_number.text.toString())
            )
        }

        tv_go1.setOnClickListener {
            startActivity(
                Intent(this, YuCeCommentActivity::class.java).putExtra("zstype", "3")
                    .putExtra("title", tv_title1.text.toString())
                    .putExtra("number", tv_number1.text.toString())
            )
        }
        tv_go2.setOnClickListener {
            startActivity(
                Intent(this, YuCeCommentActivity::class.java).putExtra("zstype", "1")
                    .putExtra("title", tv_title2.text.toString())
                    .putExtra("number", tv_number2.text.toString())
            )
        }
        tv_go.setOnClickListener {
            startActivity(
                Intent(this, YuCeCommentActivity::class.java).putExtra("zstype", "2")
                    .putExtra("title", tv_title.text.toString())
                    .putExtra("number", tv_number.text.toString())
            )
        }
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
