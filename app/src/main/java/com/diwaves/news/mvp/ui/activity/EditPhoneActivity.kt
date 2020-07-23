package com.diwaves.news.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.diwaves.news.di.component.DaggerEditPhoneComponent
import com.diwaves.news.di.module.EditPhoneModule
import com.diwaves.news.mvp.contract.EditPhoneContract
import com.diwaves.news.mvp.presenter.EditPhonePresenter

import com.diwaves.news.R
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_edit_phone.*
import java.util.concurrent.TimeUnit


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/10/2020 11:19
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
class EditPhoneActivity : BaseActivity<EditPhonePresenter>(), EditPhoneContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerEditPhoneComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .editPhoneModule(EditPhoneModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_edit_phone //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        tv_get_code.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {

        }
        rb_ok.clicks().throttleFirst(500, TimeUnit.MILLISECONDS).subscribe {

        }
        titleBar.setBackClick(View.OnClickListener {
            finish()
        })
    }

    override fun codeSuccess() {
        tv_get_code.setTextColor(ContextCompat.getColor(this, R.color.color_CECECE))
        tv_get_code.isClickable = false
    }

    override fun codeFail() {
        tv_get_code.setTextColor(ContextCompat.getColor(this, R.color.color_2BA4D9))
        tv_get_code.isClickable = true
    }

    override fun editSuccess() {
        TODO("Not yet implemented")
    }

    override fun editFail() {
        TODO("Not yet implemented")
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
