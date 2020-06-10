package com.example.szh.mvp.ui.activity

import android.content.Intent
import android.os.Bundle

import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils

import com.example.szh.di.component.DaggerRegisterComponent
import com.example.szh.di.module.RegisterModule
import com.example.szh.mvp.contract.RegisterContract
import com.example.szh.mvp.presenter.RegisterPresenter

import com.example.szh.R
import kotlinx.android.synthetic.main.activity_register.*


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 06/09/2020 11:40
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
class RegisterActivity : BaseActivity<RegisterPresenter>(), RegisterContract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerRegisterComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .registerModule(RegisterModule(this))
            .build()
            .inject(this)
    }


    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_register //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        slider.setOnSlideCompleteListener {
            slider.setEnabled(false)
            slider.setText("验证完成")
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